package com.est.spring_daily_quiz.thyme_leaf.controller;

import com.est.spring_daily_quiz.thyme_leaf.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RequestMapping("/books")
public class BookController {
    /*
    * listBooks, showAddBookForm, addBook
    * */
    private List<Book> books = new ArrayList<>();
    private long nextId = 1L;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping
    public String addBook(@ModelAttribute("book") Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    /**
     * 1. BookController 클래스에 다음 메서드를 추가하세요:
     *     - showEditBookForm: GET 요청으로 /editBook/{id} 경로를 처리합니다.
     *     - editBook: POST 요청으로 /editBook 경로를 처리합니다.
     *     - deleteBook: GET 요청으로 /deleteBook/{id} 경로를 처리합니다.
     *     - findBookById: 주어진 ID로 도서를 찾는 private 메서드입니다.
     *     - updateBook: 도서 정보를 업데이트하는 private 메서드입니다
     * */

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") long id, Model model) {
        Book foundBook = findBookById(id);
        model.addAttribute("book", foundBook);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book updateBook) {
        Book foundBook = findBookById(updateBook.getId());
        updateBook(updateBook, foundBook);
        return "redirect:/books";
    }

    @PostMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        Book foundBook = findBookById(id);
        books.remove(foundBook);
        return "redirect:/books";
    }

    private static void updateBook(Book updateBook, Book foundBook) {
        foundBook.setAuthor(updateBook.getAuthor());
        foundBook.setTitle(updateBook.getTitle());
        foundBook.setPublicationYear(updateBook.getPublicationYear());
    }

    private Book findBookById(long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
