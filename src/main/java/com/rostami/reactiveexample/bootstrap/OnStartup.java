package com.rostami.reactiveexample.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OnStartup implements CommandLineRunner {

    private final Sinks.Many<String> sink;

    @Override
    public void run(String... args) {
        Flux.interval(Duration.ofSeconds(2))
                .subscribe(e -> {
                    this.sink.tryEmitNext(
                            "Hello from server. Current time is: " + System.currentTimeMillis()
                    );
                });
    }
}