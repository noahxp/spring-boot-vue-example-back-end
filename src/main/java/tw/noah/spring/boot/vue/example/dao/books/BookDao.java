package tw.noah.spring.boot.vue.example.dao.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.noah.spring.boot.vue.example.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book,String> {

// JQL example
//  @Query("select u from User u where u.emailAddress = ?1")
//  User findByEmailAddress(String emailAddress);

// Native SQL example
//  @Query("select * from user where email_address = ?1", nativeQuery = true)
//  User findByEmailAddress(String emailAddress);

// update by JQL
//  @Modifying
//  @Query("update User set emailiAddress= :emailAddress"
//      + " where userId = :userId"
//      + " and seqContactsSettingCalllist = :seqContactsSettingCalllist")
//  int updateExample(@Param("emailAddress") String emailAddress, @Param("userId") Long userId);

// update by native
//  @Modifying
//  @Query("update User set emailiAddress= :emailAddress"
//      + " where userId = :userId"
//      + " and seqContactsSettingCalllist = :seqContactsSettingCalllist", nativeQuery = true)
//  int updateExample(@Param("emailAddress") String emailAddress, @Param("userId") Long userId);
}
