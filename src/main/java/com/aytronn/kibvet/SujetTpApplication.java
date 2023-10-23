package com.aytronn.kibvet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.Instant;

@SpringBootApplication
@Slf4j
public class SujetTpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SujetTpApplication.class, args);
    }

    /**
     * Application ready event.
     *
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        log.info("Application started {}", Instant.now());
    }
}
