package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlideDTOResponse {
    private String id;
    private String text;
    private Integer order;
    private String imageUrl;
    private OrganizationDTO organization;
}
