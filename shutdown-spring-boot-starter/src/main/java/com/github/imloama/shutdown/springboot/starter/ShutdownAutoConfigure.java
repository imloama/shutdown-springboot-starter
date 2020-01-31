package com.github.imloama.shutdown.springboot.starter;

import com.github.imloama.shutdown.springboot.starter.service.TomcatShutdownService;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ShutdownProperties.class)
public class ShutdownAutoConfigure {

    @Autowired
    ShutdownProperties properties;


    @Bean
    @ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
    public TomcatShutdownService tomcatShutdownService(){
        return new TomcatShutdownService(this.properties);
    }


}
