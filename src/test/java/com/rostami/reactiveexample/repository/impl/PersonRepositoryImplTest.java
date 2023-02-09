package com.rostami.reactiveexample.repository.impl;

import com.rostami.reactiveexample.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {
    private PersonRepositoryImpl repository;

    @BeforeEach
    void setUp(){
        repository = new PersonRepositoryImpl();
    }

    @Test
    void getByIdBlock() {
        Mono<Person> byId = repository.getById(1);
        Person block = byId.block();
        System.out.println(Objects.requireNonNull(block));
    }

    @Test
    void getByIdSubscribe(){
        Mono<Person> byId = repository.getById(1);
        StepVerifier.create(byId).expectNextCount(1).verifyComplete();
        byId.subscribe(System.out::println);
    }

    @Test
    void getByIdMapFunction(){
        Mono<Person> byId = repository.getById(1);
        byId.map(Person::getFirstName)
        .subscribe(System.out::println);
    }

    @Test
    void getAllFirstBlock() {
        Flux<Person> personFlux = repository.getAll();
        Person person = personFlux.blockFirst();
        System.out.println(person);
    }

    @Test
    void getAllSubscribe() {
        Flux<Person> personFlux = repository.getAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void getAllAsList() {
        Flux<Person> personFlux = repository.getAll();
        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> list.forEach(System.out::println));
    }

    @Test
    void fluxFilterById() {
        Flux<Person> personFlux = repository.getAll();
        final int personId = 1;
        Mono<Person> filter = personFlux
                .filter(person -> person.getId().equals(personId))
                .next();
        filter.subscribe(System.out::println);
    }

    @Test
    void fluxFilterByIdNotFound() {
        Flux<Person> personFlux = repository.getAll();
        final int personId = 8;
        Mono<Person> filter = personFlux
                .filter(person -> person.getId().equals(personId))
                .single();
        filter.doOnError(throwable -> System.out.println("On Error : " + throwable.getMessage()))
                .onErrorReturn(new Person(10, "Test", "Test"))
                .subscribe(System.out::println);
    }
}