package com.opgea.cms.service.mail;

import com.opgea.cms.dao.EmailConfigDAO;
import com.opgea.cms.domain.entities.EmailConfig;
import com.opgea.cms.domain.modal.MailModel;
import java.io.File;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;





@Service(value="mailService")
public class MailService {

        
	@Autowired
	private JavaMailSender opgeaMailSender;
        
    
        private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        @Autowired
        private EmailConfigDAO emailConfigDAO;
        
	@Autowired
	private SimpleMailMessage alertMailMessage;
	
        public Properties getSmtpProperties(){
            Properties properties = new Properties();
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.debug", "true");
            return properties;
        }
	
	
	public void sendMail(String from, String to, String subject, String body){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
	}
	
	public void sendMimeMail(MailModel mailModel, Long employeeId) throws UnsupportedEncodingException{
                EmailConfig emailConfig = emailConfigDAO.findByEmployeeId(employeeId);
                System.out.println("MailModel: "+mailModel);
                mailSender.setUsername(emailConfig.getEmailId());
                mailSender.setPassword(emailConfig.getPassword());
                mailSender.setHost(emailConfig.getSmtpHost());
                mailSender.setPort(emailConfig.getPort());
                mailSender.setJavaMailProperties(this.getSmtpProperties());
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
                    helper = new MimeMessageHelper(message, true);
                    String emailIds[] = mailModel.getTo().split(",");
                    if(emailIds.length > 1){
                        for(String email: emailIds){
                            helper.addBcc(email);
                        }
                    }else{
                      helper.setTo(mailModel.getTo());
                    }
                    helper.setSubject(mailModel.getSubject());
                    helper.setText(mailModel.getMessage(), true);
                    
                    String[] filePath = mailModel.getFilePath(); 
                    for(String pathStr : filePath){
                            FileSystemResource file = new FileSystemResource(new File(pathStr));
                            helper.addAttachment(file.getFilename(), file);
                    }
                     
                     
                } catch (javax.mail.MessagingException e) {
                        e.printStackTrace();
                }
	
		mailSender.send(message);
	}	
        
        public void sendAlertMail(String alert){
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);
	}
        
        public void sendMailToCompany(MailModel mailModel) throws UnsupportedEncodingException{
		MimeMessage message = opgeaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
                    helper = new MimeMessageHelper(message, true);
                    helper.setTo(mailModel.getTo());
                    helper.setSubject(mailModel.getSubject());
                    helper.setText(mailModel.getMessage(), true);
                } catch (javax.mail.MessagingException e) {
                        e.printStackTrace();
                }
	
		opgeaMailSender.send(message);
	}
	
	
}
