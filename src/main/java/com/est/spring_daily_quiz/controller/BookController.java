package com.est.spring_daily_quiz.controller;

import com.est.spring_daily_quiz.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>();
    private long nextId = 1L;

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping
    public String postUsers(@ModelAttribute("book") Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }
}
