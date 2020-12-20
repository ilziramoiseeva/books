package tech.itpark.jdbc.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.jdbc.mapper.BookRowMapper;
import tech.itpark.jdbc.model.Book;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BookManager {
    private final NamedParameterJdbcTemplate template;
    private final BookRowMapper rowMapper = new BookRowMapper();

    public List<Book> getAll() {
        return template.query(
                "SELECT id,category_id,name,author,price FROM books ORDER BY id LIMIT 10",
                rowMapper
        );
    }

    public Book getById(long id) {
        return template.queryForObject(
                "SELECT id,category_id,name,author,price FROM books WHERE id=:id",
                Map.of("id", id),
                rowMapper
        );
    }


    public List<Book> getByCategoryId(int categoryId) {
        return template.query(
                "SELECT id,category_id,name,author,price FROM books WHERE category_id=:category_id",
                Map.of("category_id", categoryId),
                rowMapper
        );
    }

    public Book save(Book item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(
                    "INSERT INTO books(category_id,name,author,price) VALUES  (:category_id,:name,:author,:price)",
                    new MapSqlParameterSource(Map.of(
                            "category_id", item.getCategoryId(),
                            "name", item.getName(),
                            "author", item.getAuthor(),
                            "price", item.getPrice()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }

        template.update(
                "UPDATE books SET category_id=:category_id,name=:name, author=:author,price=:price WHERE id=:id",
                Map.of(
                        "id",item.getId(),
                        "category_id", item.getCategoryId(),
                        "name", item.getName(),
                        "author", item.getAuthor(),
                        "price", item.getPrice()
                )
        );
        return getById(item.getId());
    }

    public List<Book> search(String name, String author) {
        return template.query(
                "SELECT id, category_id,name,author, price FROM books  WHERE name=:name AND author=:author",
                Map.of("name", name , "author", author),
                rowMapper
        );
    }

        public Book removeById ( long id){
            Book item = getById(id);

            template.update(
                    "DELETE FROM books WHERE id=:id",
                    Map.of("id", item.getId())
            );
            return item;
        }
    }

