/*
 * $Id$
 * $URL$
 *
 * ====================================================================
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
 * ====================================================================
 */
package org.ikasan.builder.component.endpoint;

import org.ikasan.component.endpoint.filesystem.messageprovider.FileConsumerConfiguration;
import org.ikasan.component.endpoint.quartz.consumer.MessageProvider;
import org.ikasan.spec.event.EventFactory;
import org.ikasan.spec.event.ManagedEventIdentifierService;
import org.ikasan.spec.management.ManagedResourceRecoveryManager;

/**
 * Contract for a default dbConsumerBuilder.
 *
 * @author Ikasan Development Team.
 */
public interface DbConsumerBuilder extends ScheduledConsumerBuilder
{
    DbConsumerBuilder setCriticalOnStartup(boolean criticalOnStartup);

    DbConsumerBuilder setConfiguredResourceId(String configuredResourceId);

    DbConsumerBuilder setConfiguration(FileConsumerConfiguration scheduledConsumerConfiguration);

    DbConsumerBuilder setMessageProvider(MessageProvider messageProvider);

    DbConsumerBuilder setManagedEventIdentifierService(ManagedEventIdentifierService managedEventIdentifierService);

    DbConsumerBuilder setManagedResourceRecoveryManager(ManagedResourceRecoveryManager managedResourceRecoveryManager);

    DbConsumerBuilder setEventFactory(EventFactory eventFactory);

    DbConsumerBuilder setCronExpression(String cronExpression);

    DbConsumerBuilder setEager(boolean eager);

    DbConsumerBuilder setIgnoreMisfire(boolean ignoreMisfire);

    DbConsumerBuilder setTimezone(String timezone);

    DbConsumerBuilder setScheduledJobGroupName(String scheduledJobGroupName);

    DbConsumerBuilder setScheduledJobName(String scheduledJobName);

    DbConsumerBuilder setDriver(String driver);

    DbConsumerBuilder setUrl(String url);

    DbConsumerBuilder setUsername(String username);

    DbConsumerBuilder setPassword(String password);

    DbConsumerBuilder setSqlStatement(String sqlStatement);

}
