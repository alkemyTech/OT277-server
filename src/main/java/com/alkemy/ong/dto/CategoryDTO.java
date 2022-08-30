package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDTO {

    //@NotNull(message = "El nombre no puede ser nulo")
    private String name;

    //@NotNull(message = "Debe tener una descripcion")
    private String description;

    //@NotNull(message = "Debe tener una imagen")
    private String image;

    @CreationTimestamp
    private Timestamp timestamps;

}
