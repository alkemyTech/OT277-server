package com.alkemy.ong.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class MemberDTO {

    @NotEmpty
    private String name;

    private String facebookUrl;


    private String instagramUrl;


    private String linkedinUrl;

    @NotEmpty
    private String image;


    private String description;
}
