/*
 * $Id$
 * $URL$
 *
 * =============================================================================
 * Ikasan Enterprise Integration Platform
 *
 * Distributed under the Modified BSD License.
 * Copyright notice: The copyright for this software and a full listing
 * of individual contributors are as shown in the packaged copyright.txt
 * file.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  - Neither the name of the ORGANIZATION nor the names of its contributors may
 *    be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * =============================================================================
 */
package org.ikasan.endpoint.ftp.producer;


import org.apache.log4j.Logger;
import org.ikasan.client.FileTransferConnectionTemplate;
import org.ikasan.connector.listener.TransactionCommitEvent;
import org.ikasan.connector.listener.TransactionCommitFailureListener;
import org.ikasan.filetransfer.Payload;
import org.ikasan.connector.ftp.outbound.FTPConnectionSpec;
import org.ikasan.spec.component.endpoint.EndpointException;
import org.ikasan.spec.component.endpoint.Producer;
import org.ikasan.spec.configuration.ConfiguredResource;
import org.ikasan.spec.management.ManagedResource;
import org.ikasan.spec.management.ManagedResourceRecoveryManager;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * FTP Implementation of a producer based on the JCA specification.
 *
 * @author Middleware Team
 */
public class FtpProducer implements Producer<Payload>,
        ManagedResource, ConfiguredResource<FtpProducerConfiguration>, TransactionCommitFailureListener {
    /**
     * class logger
     */
    private static Logger logger = Logger.getLogger(FtpProducer.class);

    /**
     * Connection factory
     */
    private final ConnectionFactory connectionFactory;

    /**
     * configured resource id
     */
    protected String configuredResourceId;

    /**
     * Configuration - default to vanilla instance
     */
    protected FtpProducerConfiguration configuration = new FtpProducerConfiguration();


    /**
     * Currently active connection template
     */
    protected FileTransferConnectionTemplate activeFileTransferConnectionTemplate;

    /**
     * A connection template
     */
    protected FileTransferConnectionTemplate fileTransferConnectionTemplate;

    /**
     * Alternate template to be used in cases of failure
     */
    protected FileTransferConnectionTemplate alternateFileTransferConnectionTemplate;

    private ManagedResourceRecoveryManager managedResourceRecoveryManager;

    /**
     * determines whether this managed resource failure will fail the startup of the flow
     */
    protected boolean isCriticalOnStartup = true;

    /**
     * Constructor
     *
     * @param connectionFactory FTP connection factory
     */
    public FtpProducer(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        if (this.connectionFactory == null) {
            throw new IllegalArgumentException("connectionFactory cannot be 'null'");
        }

    }

    public FtpProducerConfiguration getConfiguration() {
        return this.configuration;
    }

    public String getConfiguredResourceId() {
        return this.configuredResourceId;
    }

    public void setConfiguration(FtpProducerConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setConfiguredResourceId(String configuredResourceId) {
        this.configuredResourceId = configuredResourceId;
    }

    public void invoke(Payload payload) throws EndpointException {

            try {
                // Leave this map empty if the output directory should be used in all cases
                Map<String, String> outputTargets = new HashMap<String, String>();

                this.activeFileTransferConnectionTemplate.deliverPayload(
                        payload,
                        this.configuration.getOutputDirectory(),
                        outputTargets,
                        this.configuration.getOverwrite().booleanValue(),
                        this.configuration.getRenameExtension(),
                        this.configuration.getChecksumDelivered().booleanValue(),
                        this.configuration.getUnzip().booleanValue(),
                        this.configuration.getCleanupJournalOnComplete()
                );
            } catch (ResourceException e) {
                this.switchActiveConnection();
                throw new EndpointException(e);
            }

    }

    /**
     * Switch the active connection to the other connection template.
     */
    protected void switchActiveConnection() {
        if (this.alternateFileTransferConnectionTemplate != null) {
            if (this.activeFileTransferConnectionTemplate == this.fileTransferConnectionTemplate) {
                this.activeFileTransferConnectionTemplate = this.alternateFileTransferConnectionTemplate;
            } else {
                this.activeFileTransferConnectionTemplate = this.fileTransferConnectionTemplate;
            }
        }
    }

    /* (non-Javadoc)
    * @see org.ikasan.spec.management.ManagedResource#startManagedResource()
    */
    @Override
    public void startManagedResource() {

        configuration.validate();

        FTPConnectionSpec spec = createSpec(configuration);
        FTPConnectionSpec alternateSpec = createSpec(configuration);

        getEndpoint(spec, alternateSpec);

    }

    /* (non-Javadoc)
     * @see org.ikasan.spec.management.ManagedResource#stopManagedResource()
     */
    public void stopManagedResource() {
    }

    public boolean isCriticalOnStartup() {
        return this.isCriticalOnStartup;
    }

    public void setCriticalOnStartup(boolean isCriticalOnStartup) {
        this.isCriticalOnStartup = isCriticalOnStartup;
    }

    public void setManagedResourceRecoveryManager(ManagedResourceRecoveryManager managedResourceRecoveryManager) {
        this.managedResourceRecoveryManager = managedResourceRecoveryManager;
    }

    /**
     * Utility method to aid testing of this class
     *
     * @return
     */
    protected FTPConnectionSpec getConnectionSpec() {
        return new FTPConnectionSpec();
    }

    /* (non-Jsdoc)
    * @see org.ikasan.spec.endpoint.EndpointFactory#createEndpoint(java.lang.Object)
    */
    private FTPConnectionSpec createSpec(FtpProducerConfiguration configuration) {

        FTPConnectionSpec spec = this.getConnectionSpec();
        spec.setClientID(configuration.getClientID());
        spec.setRemoteHostname(configuration.getRemoteHost());
        spec.setMaxRetryAttempts(configuration.getMaxRetryAttempts());
        spec.setRemotePort(configuration.getRemotePort());
        spec.setConnectionTimeout(configuration.getConnectionTimeout());
        spec.setUsername(configuration.getUsername());
        spec.setCleanupJournalOnComplete(configuration.getCleanupJournalOnComplete());
        spec.setActive(configuration.getActive());
        spec.setPassword(configuration.getPassword());
        spec.setDataTimeout(configuration.getDataTimeout());
        spec.setSocketTimeout(configuration.getSocketTimeout());
        spec.setSystemKey(configuration.getSystemKey());

        return spec;
    }
    private FTPConnectionSpec createAlternateSpec(FtpProducerConfiguration configuration) {

        FTPConnectionSpec alternateSpec = null;
        if (configuration instanceof FtpProducerAlternateConfiguration) {
            FtpProducerAlternateConfiguration alternteConfig = (FtpProducerAlternateConfiguration) configuration;
            alternateSpec = this.getConnectionSpec();
            alternateSpec.setClientID(alternteConfig.getClientID());
            alternateSpec.setRemoteHostname(alternteConfig.getAlternateRemoteHost());
            alternateSpec.setMaxRetryAttempts(alternteConfig.getAlternateMaxRetryAttempts());
            alternateSpec.setRemotePort(alternteConfig.getAlternateRemotePort());
            alternateSpec.setConnectionTimeout(alternteConfig.getAlternateConnectionTimeout());
            alternateSpec.setUsername(alternteConfig.getAlternateUsername());
            alternateSpec.setCleanupJournalOnComplete(alternteConfig.getCleanupJournalOnComplete());
            alternateSpec.setActive(alternteConfig.getAlternateActive());
            alternateSpec.setPassword(alternteConfig.getAlternatePassword());
            alternateSpec.setDataTimeout(alternteConfig.getAlternateDataTimeout());
            alternateSpec.setSocketTimeout(alternteConfig.getAlternateSocketTimeout());
            alternateSpec.setSystemKey(alternteConfig.getAlternateSystemKey());
        }

       return alternateSpec;
    }


    /**
     * Internal endpoint creation method allows for easier overriding of the actual endpoint creation and simpler testing.
     *
     * @param spec
     * @param alternateSpec
     * @return
     */
    private void getEndpoint(final FTPConnectionSpec spec, final FTPConnectionSpec alternateSpec) {
        activeFileTransferConnectionTemplate = new FileTransferConnectionTemplate(this.connectionFactory, spec);
        activeFileTransferConnectionTemplate.addListener(this);

        if (alternateSpec != null) {
            alternateFileTransferConnectionTemplate = new FileTransferConnectionTemplate(this.connectionFactory, spec);
            alternateFileTransferConnectionTemplate.addListener(this);

        }
    }

    @Override
    public void commitFailureOccurred(TransactionCommitEvent event) {
        logger.info("Logging error: " + event.getException().getMessage());
        this.managedResourceRecoveryManager.recover(event.getException());
    }
}
