package com.est.spring_daily_quiz.thyme_leaf.domain;

import lombok.*;

@Getter
@Setter
@Builder
public class Product {
    private long id;
    private String name;
    private double price;
}

