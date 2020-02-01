package com.github.imloama.shutdown.springboot.starter.server;

import com.github.imloama.shutdown.springboot.starter.config.ShutdownProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TomcatServerCloseListener implements IServerCloseListener, TomcatConnectorCustomizer {


    private volatile Connector connector;

    private ShutdownProperties properties;

    private int timeout = 30;

    public ShutdownProperties getProperties() {
        return properties;
    }

    public void setProperties(ShutdownProperties properties) {
        this.properties = properties;
        this.timeout = properties.getTimeout();
    }

    public void customize(Connector connector) {
        this.connector  = connector;
    }

    public void shutdown() throws Exception {
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                log.warn("tomcat service will close");
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(timeout, TimeUnit.SECONDS)) {
                    log.warn("tomcat service will be closed forcely, because of  " + timeout + " seconds timeout! ");
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(timeout, TimeUnit.SECONDS)) {
                        log.error("tomcat service close failed!");
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }


}
