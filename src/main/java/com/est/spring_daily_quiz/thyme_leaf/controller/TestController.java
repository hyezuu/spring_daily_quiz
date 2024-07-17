package com.est.spring_daily_quiz.thyme_leaf.controller;

import com.est.spring_daily_quiz.thyme_leaf.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private List<Product> products = new ArrayList<>();
    private long nextId = 1L;

    @GetMapping("/index")
    public String test(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        Product product = Product.builder()
                .id(nextId)
                .name("상품"+nextId++)
                .price(10000)
                .build();

        products.add(product);
        model.addAttribute("products", products);
        return "products";
    }
}
