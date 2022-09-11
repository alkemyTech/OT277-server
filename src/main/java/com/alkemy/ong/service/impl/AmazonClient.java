package com.alkemy.ong.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmazonClient {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(String imgBase64, String fileName) {

        String fileUrl = "";
        try {
            File file = decodeBase64(imgBase64, fileName);
            fileUrl = s3client.getUrl(bucketName, file.getName()).toString();

            uploadFileTos3bucket(file.getName(), file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File decodeBase64(String imgBase64, String fileName) {

        try {
            String extension;
            String[] strings = imgBase64.split(",");
            byte[] data;

            if (strings[0].contains("data")) {

                switch (strings[0]) {
                    case "data:image/jpeg;base64":
                        extension = ".jpeg";
                        break;
                    case "data:image/png;base64":
                        extension = ".png";
                        break;
                    default:
                        extension = ".jpg";
                        break;
                }
                data = DatatypeConverter.parseBase64Binary(strings[1]);
            } else {
                char firstChar = imgBase64.charAt(0);
                switch (firstChar) {
                    case '/':
                        extension = ".jpeg";
                        break;
                    case 'i':
                        extension = ".png";
                        break;
                    default:
                        extension = ".jpg";
                        break;
                }
                data = DatatypeConverter.parseBase64Binary(imgBase64);
            }

            String fileFullName = fileName + extension;
            OutputStream out = new FileOutputStream(fileFullName);
            out.write(data);
            out.close();
            File file = new File(fileFullName);
            return file;

        } catch (Exception e) {
            log.error("error writing decode base64 img", e);
            throw new RuntimeException(e);
        }

    }
}