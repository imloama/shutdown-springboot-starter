package com.github.imloama.shutdown.springboot.starter.config;

import com.github.imloama.shutdown.springboot.starter.server.IServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.server.TomcatServerCloseListener;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
public class TomcatServerAutoConfiguration {
    @Autowired
    ShutdownProperties properties;

    @Bean
    @ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
    public IServerCloseListener tomcatShutdownListener(){
        TomcatServerCloseListener listener = new TomcatServerCloseListener();
        listener.setProperties(properties);
        return listener;
    }


}
