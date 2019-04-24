package tw.noah.spring.boot.vue.example.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  /**
   * PK,books identity
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="book_id")
  private int bookId;

  /**
   * book name
   */
  @Column(name="book_name")
  private String bookName;

  /**
   * book isdn 10
   */
  @Column(name="isdn")
  private String isdn;

  /**
   * book memo
   */
  @Column(name="descript")
  private String descript;


  /**
   * data create date
   */
  @Column(name="create_date")
  private Date createDate;


  /**
   * data update date
   */
  @Column(name="update_date")
  private Date updateDate;

}
