package com.github.imloama.shutdown.springboot.starter.service;

import com.github.imloama.shutdown.springboot.starter.server.IServerCloseListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Data
@Slf4j
public class GracefulShutdown implements ApplicationListener<ContextClosedEvent> {

    private IServerCloseListener serverCloseListener;

    private ThreadPoolTaskExecutor task;
    private ThreadPoolTaskScheduler scheduler;

    public void onApplicationEvent(ContextClosedEvent event) {
        if(this.serverCloseListener != null){
            try {
                this.serverCloseListener.shutdown();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

       if(this.task!=null){
           while(this.task.getActiveCount()>0){
           }
           this.task.shutdown();
       }
        if(this.scheduler!=null){
            while(this.scheduler.getActiveCount()>0){
            }
            this.scheduler.shutdown();
        }
    }
}
