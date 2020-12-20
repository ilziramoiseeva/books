package tech.itpark.jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.jdbc.manager.BookManager;
import tech.itpark.jdbc.model.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookManager manager;


    @RequestMapping
    public List<Book> getAll() {
        return manager.getAll();
    }

    @RequestMapping("categories/{categoryId}")
    public List<Book> getByCategoryId(@PathVariable int categoryId) {
        return manager.getByCategoryId(categoryId);
    }

    @RequestMapping("/{id}")
    public Book getById(@PathVariable long id) {
        return manager.getById(id);
    }

    @RequestMapping("/{id}/save")
    public Book save(
            @PathVariable long id,
            @RequestParam int categoryId,
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam int price
    ) {
        return manager.save(new Book(id,categoryId, name, author, price));
    }

    @RequestMapping("/search")
    public List<Book> search(
            @RequestParam String name ,
            @RequestParam String  author) {
        return manager.search(name, author);
    }
    @RequestMapping("/{id}/remove")
    public Book removeById(@PathVariable long id) {
        return manager.removeById(id);
    }
}
