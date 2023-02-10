package com.rostami.reactiveexample.controller;

import com.rostami.reactiveexample.domain.Movie;
import com.rostami.reactiveexample.domain.MovieEvent;
import com.rostami.reactiveexample.service.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id){
        return movieService.findById(id);
    }

    @GetMapping("")
    public Flux<Movie> getAllMovies(){
        return movieService.findAll();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> movieEvents(@PathVariable String id){
        return movieService.streamMovieEvents(id);
    }
}
