package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideDTO {

    private String text;
    private Integer order;
    private String organization_id;
    private String image_b64;
}
