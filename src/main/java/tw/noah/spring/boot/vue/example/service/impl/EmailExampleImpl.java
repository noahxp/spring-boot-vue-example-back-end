package tw.noah.spring.boot.vue.example.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tw.noah.spring.boot.vue.example.service.EmailExample;

@Service
public class EmailExampleImpl implements EmailExample {


  @Autowired
  private JavaMailSender mailSender;

  @Override
  public void demoSpringMail() {

    String replay = "no-reply@104.com.tw";

    String from = "104Hunter<hunter@104.com.tw>";
    String to = "noah.liu@104.com.tw;peter.tsai@104.com.tw;howie.hung@104.com.tw";
    String subject = "這是很好的主旨，彣碁飝尛龘鑆";
    String text = "<html><body><h1 style='color:red'>你想不到的信件</h1>永遠不要相信你看到的！<br></body></html>";

//    System.setProperty("mail.mime.splitlongparameters", "false");

    MimeMessage mime = mailSender.createMimeMessage();
    MimeMessageHelper helper = null;
    try {
      helper = new MimeMessageHelper(mime, true, "utf-8");
      helper.setFrom(from);
      helper.setTo(to.split(";"));
      helper.setReplyTo(replay);
      helper.setSubject(subject);
      helper.setText(text,true);

//      System.setProperty("mail.mime.encodeparameters", "false");


      helper.addInline("file-a", new File("/tmp/a"));
      helper.addInline("file-b", new File("/tmp/b"));

      helper.addAttachment("寄送推薦檔案_獵才CRM上線測試用1.pdf", new File("/tmp/c"));
      helper.addAttachment("寄送推薦檔案_獵才CRM上線測試用3.pdf", new File("/tmp/c"));

      helper.addAttachment("獵才CRM上線測試用5.pdf", new File("/tmp/c"));
      helper.addAttachment("獵才CRM上線測試用7.pdf", new File("/tmp/c"));

      helper.addAttachment("飝焱鑆-中文檔名超長會有Bug，一定要測長檔名.txt", new File("/tmp/c"));


      helper.addAttachment("104Shortlist-機密-和億生活-財會主管-溫哲毅.pdf", new File("/tmp/c"));

//      helper.addAttachment(MimeUtility.encodeText("寄送推薦檔案_獵才CRM上線測試用1.pdf", "UTF-8", "B"), new File("/tmp/c"));
//      helper.addAttachment(MimeUtility.encodeWord("寄送推薦檔案_獵才CRM上線測試用3.pdf", "UTF-8", "B"), new File("/tmp/c"));
//
//      helper.addAttachment(MimeUtility.encodeText("獵才CRM上線測試用5.pdf", "UTF-8", "B"), new File("/tmp/c"));
//      helper.addAttachment(MimeUtility.encodeWord("獵才CRM上線測試用7.pdf", "UTF-8", "B"), new File("/tmp/c"));
//
//      helper.addAttachment(MimeUtility.encodeText("飝焱鑆-中文檔名超長會有Bug，一定要測長檔名.txt", "UTF-8", "B"), new File("/tmp/c"));
//
//
//      helper.addAttachment(MimeUtility.encodeWord("104Shortlist-機密-和億生活-財會主管-溫哲毅.pdf", "UTF-8", "B"), new File("/tmp/c"));


      mailSender.send(mime);

    } catch (MessagingException e ) {
      e.printStackTrace();
    }

  }


  public static void main(String... args) throws UnsupportedEncodingException {
    System.out.println(MimeUtility.encodeWord("104刪帳號說明.pdf", "UTF-8", "B"));
    System.out.println(MimeUtility.encodeWord(MimeUtility.encodeWord("104刪帳號說明.pdf", "UTF-8", "B")));
//
//    System.out.println(MimeUtility.encodeWord("寄送推薦檔案_獵才CRM上線測試用.pdf", "UTF-8", "B"));
//    System.out.println(MimeUtility.encodeText("寄送推薦檔案_獵才CRM上線測試用.pdf", "UTF-8", "B"));
//    System.out.println(MimeUtility.encodeText(new String("寄送推薦檔案獵才CR中線測試用.pdf".getBytes(),"UTF-8"), "UTF-8", "B"));
//
//    System.out.println(MimeUtility.encodeWord("104刪帳號說明.pdf", "UTF-8", "B"));
  }
}
