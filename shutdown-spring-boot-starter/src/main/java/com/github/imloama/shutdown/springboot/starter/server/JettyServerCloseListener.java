package com.github.imloama.shutdown.springboot.starter.server;

import com.github.imloama.shutdown.springboot.starter.config.ShutdownProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Connector;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;

@Slf4j
public class JettyServerCloseListener implements IServerCloseListener, JettyServerCustomizer {

    private volatile org.eclipse.jetty.server.Server server;

    private ShutdownProperties properties;

    public ShutdownProperties getProperties() {
        return properties;
    }

    public void setProperties(ShutdownProperties properties) {
        this.properties = properties;
    }

    public void shutdown() throws Exception {
        for (Connector connector : server.getConnectors()){
            connector.shutdown();
        }
        new Thread()
        {
            @Override
            public void run()
            {
                try{
                    server.stop();
                }catch (InterruptedException e){
                    log.info(e.getMessage(), e);
                }catch (Exception e){
                    throw new RuntimeException("Shutting down server", e);
                }
            }
        }.start();
    }

    public void customize(org.eclipse.jetty.server.Server server) {
        this.server = server;
    }
}
