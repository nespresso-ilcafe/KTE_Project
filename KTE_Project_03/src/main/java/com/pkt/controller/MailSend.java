package com.pkt.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/member/")
@Log4j
public class MailSend {


    @GetMapping("email")
    public Map<String, String> doMail(String email, HttpSession session) throws ServletException, IOException {
        log.info("email:" + email);

        String receiver = email;
        String subject = "�̸��� ���� ��ȣ"; // �̸��� ������ ����
        String verificationCode = generateVerificationCode(); // �������� ������ ������ȣ

        // Session ��ü�� ����Ͽ� �̸����� ������ �ڵ�� ���⼭����
        String user = "zzxas2@gmail.com";
        String password = "ermrglsiczpgnzdu"; // �� ��й�ȣ ���

        Map<String, String> response = new HashMap<>();

        try {
            Properties p = new Properties();
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.port", "587");

            Session s = Session.getInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            Message m = new MimeMessage(s);
            Address receiver_address = new InternetAddress(receiver);

            m.setHeader("content-type", "text/html;charset=utf-8");
            m.addRecipient(Message.RecipientType.TO, receiver_address);
            m.setSubject(subject);
            m.setContent(verificationCode, "text/html;charset=utf-8");
            m.setSentDate(new Date());

            Transport.send(m);

            // ���ǿ� ������ȣ�� ����
            session.setAttribute("verificationCode", verificationCode);

            response.put("status", "success");
            response.put("verificationCode", verificationCode);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "fail");
            return response;
        }
    }

    // �������� 6�ڸ� ������ȣ �����ϴ� �޼���
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10)); // 0���� 9������ ���� �� �������� ����
        }
        return sb.toString();
    }
}