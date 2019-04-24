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
@Table(name = "book_edit_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEditLog {

  /**
   * PK,books identity
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="log_id")
  private int logId;

  /**
   * old book name
   */
  @Column(name="book_name_old")
  private String bookNameOld;

  /**
   * new book name
   */
  @Column(name="book_name_new")
  private String bookNameNew;

  /**
   * old book isdn 10
   */
  @Column(name="isdn_old")
  private String isdnOld;

  /**
   * new book isdn 10
   */
  @Column(name="isdn_new")
  private String isdnNew;


  /**
   * editor employee no
   */
  @Column(name="editor_emp_no")
  private String editorEmpNo;


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
