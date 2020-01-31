package com.github.imloama.shutdown.springboot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "shutdown")
public class ShutdownProperties {

    private int timeout = 30;

}
