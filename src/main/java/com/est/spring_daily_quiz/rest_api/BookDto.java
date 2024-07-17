package com.est.spring_daily_quiz.rest_api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDto {

    private String title;
    private String author;
    private String isbn;
    private String publishedYear;
}
