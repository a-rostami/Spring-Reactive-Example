package com.rostami.reactiveexample.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class Movie {
    private String id;
    @NonNull
    private String title;
}
