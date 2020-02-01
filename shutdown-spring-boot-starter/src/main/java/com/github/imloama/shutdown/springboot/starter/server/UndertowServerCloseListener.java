package com.github.imloama.shutdown.springboot.starter.server;

import com.github.imloama.shutdown.springboot.starter.config.ShutdownProperties;
import io.undertow.Undertow;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;

public class UndertowServerCloseListener implements UndertowBuilderCustomizer, IServerCloseListener {

    private ShutdownProperties properties;

    private int timeout = 30;

    public ShutdownProperties getProperties() {
        return properties;
    }

    public void setProperties(ShutdownProperties properties) {
        this.properties = properties;
        this.timeout = properties.getTimeout();
    }

    public void shutdown() throws Exception {
        throw new Exception("not working!");
    }

    public void customize(Undertow.Builder builder) {
        //TODO
    }
}
