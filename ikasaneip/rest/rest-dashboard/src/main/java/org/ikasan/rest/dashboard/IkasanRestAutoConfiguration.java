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
package org.ikasan.rest.dashboard;

import org.ikasan.security.service.UserService;
import org.ikasan.spec.cache.FlowStateCacheAdapter;
import org.ikasan.spec.metadata.ModuleMetaDataService;
import org.ikasan.spec.persistence.BatchInsert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import javax.annotation.Resource;

@Configuration
public class IkasanRestAutoConfiguration
{
    @Resource
    private BatchInsert errorOccurrenceBatchInsert;

    @Resource
    private BatchInsert replayEventBatchInsert;

    @Resource
    private BatchInsert exclusionEventBatchInsert;

    @Resource
    private BatchInsert wiretapEventBatchInsert;

    @Resource
    private BatchInsert systemEventBatchInsert;

    @Resource
    private BatchInsert moduleMetadataBatchInsert;

    @Resource
    private BatchInsert configurationMetadataBatchInsert;

    @Resource
    @Qualifier("moduleMetadataService")
    private ModuleMetaDataService moduleMetadataService;

    @Resource
    private FlowStateCacheAdapter cacheAdapter;

    @Bean
    public ReplayController replayApplication()
    {
        return new ReplayController(this.replayEventBatchInsert);
    }

    @Bean
    public WiretapController wiretapController()
    {
        return new WiretapController(this.wiretapEventBatchInsert);
    }

    @Bean
    public ErrorController errorApplication()
    {
        return new ErrorController(this.errorOccurrenceBatchInsert);
    }

    @Bean
    public ExclusionController exclusionApplication()
    {
        return new ExclusionController(this.exclusionEventBatchInsert);
    }

    @Bean
    public MetaDataController metaDataApplication()
    {
        return new MetaDataController(this.moduleMetadataBatchInsert,
            this.configurationMetadataBatchInsert);
    }

    @Bean
    public SystemEventController systemEventController()
    {
        return new SystemEventController(this.systemEventBatchInsert);
    }

    @Bean
    public ModulesController modulesController( )
    {
        return new ModulesController(moduleMetadataService);
    }

    @Bean
    public MetricsController metricsApplication()
    {
        return new MetricsController();
    }

    @Bean
    public UserController userController(UserService userService)
    {
        return new UserController(userService);
    }

    @Bean
    public NotifierController notifierControllerApplication()
    {
        return new NotifierController(this.cacheAdapter);
    }

    @Bean
    public JwtAuthenticationController jwtAuthenticationController(AuthenticationManager authenticationManager,
                                                                   JwtTokenUtil jwtTokenUtil, UserService userService) {
        return new JwtAuthenticationController(authenticationManager, jwtTokenUtil, userService);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter(UserService userService, JwtTokenUtil jwtTokenUtil) {
        return new JwtRequestFilter(userService, jwtTokenUtil);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }
}
