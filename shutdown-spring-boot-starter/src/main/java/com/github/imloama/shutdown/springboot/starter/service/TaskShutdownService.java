package com.github.imloama.shutdown.springboot.starter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Slf4j
public class TaskShutdownService implements ApplicationListener<ContextClosedEvent> {

    private ThreadPoolTaskExecutor task;
    private ThreadPoolTaskScheduler scheduler;

    public TaskShutdownService(ThreadPoolTaskExecutor task, ThreadPoolTaskScheduler scheduler){
        this.task = task;
        this.task.setWaitForTasksToCompleteOnShutdown(true);
        this.scheduler = scheduler;
        this.scheduler.setWaitForTasksToCompleteOnShutdown(true);
    }


    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        while(this.task.getActiveCount()>0){
        }
        this.task.shutdown();
        while(this.scheduler.getActiveCount()>0){
        }
        this.scheduler.shutdown();
    }
}
