package com.est.spring_daily_quiz.rest_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    List<Book> books = new ArrayList<>();
    long bookId = 1L;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        books.add(convertBookDtoToBook(bookDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(convertBookListToBookDtoList(books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") long id) {
        return ResponseEntity.ok(convertBookToBookDto(getBookById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") long id, @RequestBody BookDto bookDto) {
        Book book = getBookById(id);

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPublishedYear(bookDto.getPublishedYear());

        return ResponseEntity.ok(convertBookToBookDto(book));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        books.remove(getBookById(id));
        return ResponseEntity.noContent().build();
    }

    public Book getBookById(@PathVariable long bookId) {
        return books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public Book convertBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookId++)
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .publishedYear(bookDto.getPublishedYear())
                .build();
    }

    public BookDto convertBookToBookDto(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedYear(book.getPublishedYear())
                .build();
    }

    public List<BookDto> convertBookListToBookDtoList(List<Book> books) {
        return books.stream().map(this::convertBookToBookDto)
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
