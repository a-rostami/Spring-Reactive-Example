package com.rostami.reactiveexample.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class MovieEvent {
    private String movieId;
    private Date movieDate;
}
