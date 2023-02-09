package com.rostami.reactiveexample.repository.impl;

import com.rostami.reactiveexample.domain.Person;
import com.rostami.reactiveexample.repository.PersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {
    private final Person david = new Person(1, "David", "James");
    private final Person sam = new Person(2, "Sam", "Sve");
    private final Person ellie = new Person(3, "Ellie", "Sym");

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(david);
    }

    @Override
    public Flux<Person> getAll() {
        return Flux.just(david, sam, ellie);
    }
}
