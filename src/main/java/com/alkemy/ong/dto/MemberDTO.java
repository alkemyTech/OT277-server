package com.alkemy.ong.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
public class MemberDTO {

    @NotEmpty(message="Name cannot be empty")
    private String name;

    private String facebookUrl;


    private String instagramUrl;


    private String linkedinUrl;

    @NotEmpty(message="Image cannot be empty")
    private String image;


    private String description;

    private Timestamp timestamp;
}
