package com.jiamj.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Parent {
    private Logger logger = LoggerFactory.getLogger(Parent.class);
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Parent() {
        // Mimic Web app, save common info in MDC
        MDC.put("IP", "192.168.1.1");
    }

    public void runMultiThreadByExecutor() throws InterruptedException {
        logger.info("Before start child thread");

        executorService.submit(new Child());
        logger.info("After start child thread");

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        logger.info("ExecutorService is over");
    }

    public static void main(String[] args) throws InterruptedException {
        Parent parent = new Parent();
        parent.runMultiThreadByExecutor(); // MDC OK
    }
}
