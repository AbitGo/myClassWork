package com.Mail;

import com.DemoApplication;
import com.pojo.FormWelcome;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Component
public class SendMailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendEmail;
    /***
     * @function 发送带html的邮件
     * @tips
     * @param
     * @return 无返回值
     * @throws MessagingException
     */
    public void sendHtmlMail(String from, String to, String subject, String content)
    {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setTo(to);
            helper.setFrom(sendEmail);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送失败");
        }
    }

    public void sendHtmlMail(String welcome,long startTime,long endTime){
        try{
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            //ClassLoader loader = DemoApplication.class.getClassLoader();
            //configuration.setClassLoaderForTemplateLoading(loader,"ftl");

            ClassLoader loader = DemoApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader,"ftl");
            Template template = configuration.getTemplate("maitemplate.ftl");
            StringWriter mail = new StringWriter();

            //通过格式转化
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start = sdf.format(new Date(Long.parseLong(String.valueOf(startTime*1000))));
            String end = sdf.format(new Date(Long.parseLong(String.valueOf(endTime*1000))));
            FormWelcome formWelcome = new FormWelcome(welcome,start,end);
            template.process(formWelcome,mail);
            sendHtmlMail("15695203200@163.com","1419561484@qq.com","每日防疫体温提交",
                    mail.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
