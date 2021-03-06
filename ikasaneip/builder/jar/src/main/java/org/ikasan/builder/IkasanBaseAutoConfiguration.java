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
package org.ikasan.builder;

import org.ikasan.module.IkasanModuleAutoConfiguration;
import org.ikasan.rest.module.IkasanRestAutoConfiguration;
import org.ikasan.transaction.IkasanTransactionConfiguration;
import org.ikasan.web.IkasanWebAutoConfiguration;
import org.ikasan.web.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource( {
        "classpath:exception-conf.xml",
        "classpath:ikasan-transaction-conf.xml",
        "classpath:ikasan-transaction-pointcut-resubmission.xml",
        "classpath:ikasan-transaction-pointcut-quartz.xml",
        "classpath:serialiser-service-conf.xml",
        "classpath:scheduler-service-conf.xml",
        "classpath:error-reporting-service-conf.xml",
        "classpath:recoveryManager-service-conf.xml",
        "classpath:monitor-service-conf.xml",
        "classpath:filter-service-conf.xml",
        "classpath:configuration-service-conf.xml",
        "classpath:systemevent-service-conf.xml",
        "classpath:replay-service-conf.xml",
        "classpath:wiretap-service-conf.xml",
        "classpath:hospital-conf.xml",
        "classpath:exclusion-service-conf.xml",
        "classpath:topology-conf.xml",
        "classpath:topology-tx-conf.xml",
        "classpath:datasource-conf.xml",
        "classpath:security-service-boot-conf.xml",
        "classpath:springapp-servlet-boot.xml",

} )
@Import({ IkasanTransactionConfiguration.class, IkasanWebAutoConfiguration.class, IkasanModuleAutoConfiguration.class,WebSecurityConfig.class,IkasanRestAutoConfiguration.class})
public class IkasanBaseAutoConfiguration
{


    @Bean
    public BuilderFactory builderFactory(){
         return new BuilderFactory();
    }
    @Bean
    public AopProxyProvider aopProxyProvider()
    {
        return new AopProxyProviderSpringImpl();
    }
}
