package com.rostami.reactiveexample.service;

import com.rostami.reactiveexample.domain.Movie;
import com.rostami.reactiveexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
    Mono<Movie> findById(String id);

    Flux<Movie> findAll();

    Flux<MovieEvent> streamMovieEvents(String id);
}
