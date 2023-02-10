package com.rostami.reactiveexample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class RSocketController {

    private final Flux<String> stream;

    @MessageMapping("my.time-updates.stream")
    public Flux<String> getTimeUpdatesStream() {
        return stream;
    }
}
