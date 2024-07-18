package com.est.spring_daily_quiz.rest_api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BookService {

    List<Book> books = new ArrayList<>();
    long bookId = 1L;


    public List<BookDto> getAllBooks() {
        return convertBookListToBookDtoList(books);
    }

    public BookDto getBookById(@PathVariable long bookId) {
        Book foundBook = findBookById(bookId);
        return convertBookToBookDto(foundBook);
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = convertBookDtoToBook(bookDto);
        book.setId(bookId++);
        books.add(book);
        return convertBookToBookDto(book);
    }

    public BookDto updateBook(long id, BookDto bookDto) {
        Book book = findBookById(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPublishedYear(bookDto.getPublishedYear());
        return convertBookToBookDto(book);
    }

    public boolean deleteBook(long id) {
        return books.remove(findBookById(id));
    }

    private Book findBookById(long bookId) {
        return books.stream()
            .filter(book -> book.getId() == bookId)
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    //BookDto -> Book
    public Book convertBookDtoToBook(BookDto bookDto) {
        return Book.builder()
            .id(bookId++)
            .title(bookDto.getTitle())
            .author(bookDto.getAuthor())
            .isbn(bookDto.getIsbn())
            .publishedYear(bookDto.getPublishedYear())
            .build();
    }

    //Book -> BookDto
    public BookDto convertBookToBookDto(Book book) {
        return BookDto.builder()
            .title(book.getTitle())
            .author(book.getAuthor())
            .isbn(book.getIsbn())
            .publishedYear(book.getPublishedYear())
            .build();
    }

    //List<Book> -> List<BookDto>
    public List<BookDto> convertBookListToBookDtoList(List<Book> books) {
        return books.stream().map(this::convertBookToBookDto)
            .collect(Collectors.toList());
    }

}
