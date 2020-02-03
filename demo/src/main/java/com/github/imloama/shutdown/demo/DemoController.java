package com.github.imloama.shutdown.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        log.debug("into hello controller");
        Thread.sleep(10000);
        log.debug("will return hello controller");
        return "hello,world!";
    }

}
