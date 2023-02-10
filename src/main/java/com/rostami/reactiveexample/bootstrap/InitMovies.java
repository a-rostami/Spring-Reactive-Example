package com.rostami.reactiveexample.bootstrap;

import com.rostami.reactiveexample.domain.Movie;
import com.rostami.reactiveexample.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitMovies implements CommandLineRunner {
    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                .thenMany(Flux.just("movie 1", "movie 2", "movie 3")
                        .map(title -> Movie.builder().title(title).build())
                        .flatMap(movieRepository::save)
                ).subscribe(null, null, () ->
                        movieRepository.findAll().subscribe(System.out::println)
                );
    }
}
