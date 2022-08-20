package com.alkemy.ong.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SimpleOrganizationDTO {
    @NonNull
    private String name;
    @NonNull
    private String image;
    @NonNull
    private int phone;
    @NonNull
    private String address;
}
