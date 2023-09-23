package com.cars.service;

import com.cars.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

//    public boolean testEmail(){
//        try {
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setTo(environment.getProperty("to.email"));
//            simpleMailMessage.setSubject("Web Tools Final Project Email");
//            simpleMailMessage.setText("Reservation is fixed!!");
//            simpleMailMessage.setFrom(environment.getProperty("from.email"));
//
//            javaMailSender.send(simpleMailMessage);
//
//            return true;
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return false;
//        }
//    }
// getUserByUsername
    public boolean reservation(User user){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getUsername());//should be email address
            simpleMailMessage.setSubject("Car Reservation Success");

            //String loginUrl = appUrl+"/login";

            simpleMailMessage.setText(String.format("Dear %s, Thank you for car reservation", user.getUsername()));
            simpleMailMessage.setFrom(environment.getProperty("from.email"));

            javaMailSender.send(simpleMailMessage);

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}