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
package com.ikasan.sample.spring.boot.builderpattern;

import org.ikasan.builder.BuilderFactory;
import org.ikasan.builder.ModuleBuilder;
import org.ikasan.builder.OnException;
import org.ikasan.spec.flow.Flow;
import org.ikasan.spec.module.Module;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 *
 * @author Ikasan Development Team
 */
@org.springframework.context.annotation.Configuration
public class ModuleConfig
{
    @Resource
    private BuilderFactory builderFactory;
    @Resource
    private ComponentFactory componentFactory;

    @Bean
    public Module getModule()
    {
        // get the builders
        ModuleBuilder moduleBuilder = builderFactory.getModuleBuilder("Transaction Test Module");

        // jms consumer flow 1
        Flow jmsConsumer1Flow = moduleBuilder.getFlowBuilder("jmsToDevNullFlow1")
                .withDescription("First JMS Consuming flow.")
                .withExceptionResolver(builderFactory.getExceptionResolverBuilder().addExceptionToAction(RuntimeException.class, OnException.retryIndefinitely(1000)))
                .consumer("JMS Consumer", componentFactory.getJmsConsumer("jmsConsumer1"))
                .producer("Dev Null Producer", builderFactory.getComponentBuilder().logProducer()  .build())
                .build();

        Module module = moduleBuilder.withDescription("Test DB module.")
                .addFlow(jmsConsumer1Flow)
            .build();

        return module;
    }
}
