package com.ikasan.sample.spring.boot.builderpattern;

import org.ikasan.builder.*;
import org.ikasan.builder.component.ComponentBuilder;
import org.ikasan.component.converter.jms.ObjectMessageToObjectConverter;
import org.ikasan.component.converter.xml.*;
import org.ikasan.spec.component.endpoint.EndpointException;
import org.ikasan.spec.component.endpoint.Producer;
import org.ikasan.spec.component.transformation.Converter;
import org.ikasan.spec.flow.Flow;
import org.ikasan.spec.module.Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@Configuration
@ImportResource( {
        "classpath:ikasan-transaction-pointcut-jms.xml",
        "classpath:ikasan-transaction-pointcut-resubmission.xml",
        "classpath:ikasan-transaction-pointcut-quartz.xml",
        "classpath:h2-datasource-conf.xml"
} )
public class ModuleConfig
{
    @Resource BuilderFactory builderFactory;

    @Value("${jms.provider.url}")
    private String brokerUrl;

    @Value("${jms.connectionFactory.jndi.name}")
    private String jmsConnectionFactoryName;

    @Value("${jms.naming.factory.initial}")
    private String jmsNamingFactoryInitial;

    @Value("${jms.source.destination}")
    private String jmsSourceDestination;

    @Value("${jms.target.destination}")
    private String jmsTargetDestination;

    @Bean
    public Module getModule()
    {

        // get a module builder from the ikasanApplication
        ModuleBuilder moduleBuilder = builderFactory.getModuleBuilder("sample-builder-pattern").withDescription("Example module with pattern builder");

        // get an instance of flowBuilder from the moduleBuilder and create a flow
        Flow jmsFlow = getJmsFlow(moduleBuilder, builderFactory.getComponentBuilder());

        // add flows to the module
        Module module = moduleBuilder.addFlow(jmsFlow).build();

        return module;
    }

    public Flow getJmsFlow(ModuleBuilder moduleBuilder,ComponentBuilder componentBuilder) {
        FlowBuilder flowBuilder = moduleBuilder.getFlowBuilder("Jms Flow");

        return flowBuilder.withDescription("Jms flow description")
            .consumer("consumer", componentBuilder.jmsConsumer().setConfiguredResourceId("configuredResourceId")
                .setDestinationJndiName(jmsSourceDestination)
                .setConnectionFactoryName(jmsConnectionFactoryName)
                .setConnectionFactoryJndiPropertyFactoryInitial(jmsNamingFactoryInitial)
                .setConnectionFactoryJndiPropertyProviderUrl(brokerUrl)
                .setDestinationJndiPropertyFactoryInitial(jmsNamingFactoryInitial)
                .setDestinationJndiPropertyProviderUrl(brokerUrl)
                .setAutoContentConversion(true)
                .build()
            )
            .converter("XML String to JAXB Object", this.getConverter())
            .producer("producer", componentBuilder.jmsProducer()
                .setConfiguredResourceId("crid")
                .setDestinationJndiName(jmsTargetDestination)
                .setConnectionFactoryName(jmsConnectionFactoryName)
                .setConnectionFactoryJndiPropertyFactoryInitial(jmsNamingFactoryInitial)
                .setConnectionFactoryJndiPropertyProviderUrl(brokerUrl)
                .setDestinationJndiPropertyFactoryInitial(jmsNamingFactoryInitial)
                .setDestinationJndiPropertyProviderUrl(brokerUrl).build()
            )
            .build();

    }

    private Converter getConverter()
    {
        XmlStringToObjectConfiguration configuration = new XmlStringToObjectConfiguration();

        Class[] classes = new Class[1];
        classes[0] = MyJaxb.class;

        configuration.setClassesToBeBound(classes);

        XmlStringToObjectConverter converter =  new XmlStringToObjectConverter();
        converter.setConfiguredResourceId("id");
        converter.setConfiguration(configuration);

        return converter;
    }
}
