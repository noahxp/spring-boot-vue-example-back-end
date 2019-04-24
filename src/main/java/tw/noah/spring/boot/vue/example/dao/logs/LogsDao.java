package tw.noah.spring.boot.vue.example.dao.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.noah.spring.boot.vue.example.entity.BookEditLog;

@Repository
public interface LogsDao extends JpaRepository<BookEditLog,Integer> {

}
