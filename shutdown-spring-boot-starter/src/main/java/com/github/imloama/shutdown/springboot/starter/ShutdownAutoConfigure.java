package com.github.imloama.shutdown.springboot.starter;

import com.github.imloama.shutdown.springboot.starter.config.JettyServerAutoConfiguration;
import com.github.imloama.shutdown.springboot.starter.config.ShutdownProperties;
import com.github.imloama.shutdown.springboot.starter.config.TomcatServerAutoConfiguration;
import com.github.imloama.shutdown.springboot.starter.server.IServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.server.JettyServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.server.TomcatServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.server.UndertowServerCloseListener;
import com.github.imloama.shutdown.springboot.starter.service.GracefulShutdown;
import io.undertow.Undertow;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.Loader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.xnio.SslClientAuthMode;

@Configuration
@EnableConfigurationProperties(ShutdownProperties.class)
@AutoConfigureAfter(ServletWebServerFactoryAutoConfiguration.class)
@Import({TomcatServerAutoConfiguration.class, JettyServerAutoConfiguration.class})
public class ShutdownAutoConfigure {

    @Autowired(required = false)
    IServerCloseListener listener;
    @Autowired(required = false)
    ThreadPoolTaskExecutor task;
    @Autowired(required = false)
    ThreadPoolTaskScheduler scheduler;


//    @Bean
//    @ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
//    public IServerCloseListener tomcatShutdownListener(){
//        TomcatServerCloseListener listener = new TomcatServerCloseListener();
//        listener.setProperties(properties);
//        return listener;
//    }

//    @Bean
//    @ConditionalOnClass({Server.class, Loader.class, WebAppContext.class  })
//    public IServerCloseListener jettyShutdownListener(){
//        JettyServerCloseListener listener = new JettyServerCloseListener();
//        listener.setProperties(properties);
//        return listener;
//    }

//    @Bean
//    @ConditionalOnClass({Undertow.class, SslClientAuthMode.class })
//    public IServerCloseListener tomcatShutdownService(){
//        UndertowServerCloseListener listener = new UndertowServerCloseListener();
//        listener.setProperties(properties);
//        return listener;
//    }
//
//
    @Bean
    public GracefulShutdown gracefulShutdown(){
        GracefulShutdown shutdown = new GracefulShutdown();
        shutdown.setScheduler(scheduler);
        shutdown.setServerCloseListener(listener);
        shutdown.setTask(task);
        return shutdown;
    }

}
