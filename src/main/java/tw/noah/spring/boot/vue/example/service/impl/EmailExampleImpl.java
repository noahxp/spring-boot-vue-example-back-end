package tw.noah.spring.boot.vue.example.service.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
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
    String to = "noah.liu@104.com.tw";
    String subject = "這是很好的主旨，彣碁飝尛龘鑆";
    String text = "<html><body><h1 style='color:red'>你想不到的信件</h1>永遠不要相信你看到的！</body></html>";

    MimeMessage mime = mailSender.createMimeMessage();
    MimeMessageHelper helper = null;
    try {
      helper = new MimeMessageHelper(mime, true, "utf-8");
      helper.setFrom(from);
      helper.setTo(to);
      helper.setReplyTo(replay);
      helper.setSubject(subject);
      helper.setText(text,true);


      helper.addInline("file-a", new File("/tmp/a"));
      helper.addInline("file-b", new File("/tmp/b"));


      mailSender.send(mime);

    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }
}
