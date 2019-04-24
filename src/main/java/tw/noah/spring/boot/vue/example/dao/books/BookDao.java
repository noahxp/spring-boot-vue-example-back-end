package tw.noah.spring.boot.vue.example.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.noah.spring.boot.vue.example.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book,String> {

}
