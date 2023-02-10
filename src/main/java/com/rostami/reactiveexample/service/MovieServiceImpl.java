package com.rostami.reactiveexample.service;

import com.rostami.reactiveexample.domain.Movie;
import com.rostami.reactiveexample.domain.MovieEvent;
import com.rostami.reactiveexample.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Mono<Movie> findById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink ->
                movieEventSynchronousSink.next(new MovieEvent(id, new Date()))
                ).delayElements(Duration.ofSeconds(1));
    }
}
