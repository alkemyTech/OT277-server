package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.ong.email.sender}")
    private String emailSender;

    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public void sendEmailTo(UserDto userDto){
        String apiKey = env.getProperty("EMAIL_API_KEY");
        OrganizationEntity organizationEntity = organizationRepository.findAll().get(0);

        Mail mail = new Mail();
        mail.setFrom(new Email(emailSender,"Bienvenido a "+organizationEntity.getName()));
        mail.setTemplateId("d-1106b5befbfa42f990ffeb469ec98281");



        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("user_name",userDto.getFirstName());
        personalization.addDynamicTemplateData("ong_name",organizationEntity.getName());
        personalization.addDynamicTemplateData("ong_email",organizationEntity.getEmail());
        personalization.addDynamicTemplateData("ong_phone",organizationEntity.getPhone());
        personalization.setSubject("Registro realizado con exito");

        personalization.addTo(new Email(userDto.getEmail()));

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }
        catch (IOException e){
        }

    }

}
