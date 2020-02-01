package com.github.imloama.shutdown.springboot.starter.server;

public interface IServerCloseListener {

    void shutdown() throws Exception;

}
