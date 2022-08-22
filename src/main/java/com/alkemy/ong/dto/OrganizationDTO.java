package com.alkemy.ong.dto;


import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class OrganizationDTO {
    private String name;

    private String image;

    private String address;

    private Integer phone;

    private String email;

    private String welcomeText;

    private String aboutUsText;

    private Timestamp timestamp;

    private boolean softDelete;
}
