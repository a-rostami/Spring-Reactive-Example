package com.rostami.reactiveexample.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
}
