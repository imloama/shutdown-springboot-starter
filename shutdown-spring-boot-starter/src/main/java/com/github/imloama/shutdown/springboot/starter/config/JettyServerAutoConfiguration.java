package com.github.imloama.shutdown.springboot.starter.config;

import com.github.imloama.shutdown.springboot.starter.server.IServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.server.JettyServerCloseListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.Loader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Server.class, Loader.class, WebAppContext.class  })
public class JettyServerAutoConfiguration {
    @Autowired
    ShutdownProperties properties;

    @Bean
    public IServerCloseListener jettyShutdownListener(){
        JettyServerCloseListener listener = new JettyServerCloseListener();
        listener.setProperties(properties);
        return listener;
    }



}
