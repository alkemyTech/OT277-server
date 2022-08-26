package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDto {

    //@NotNull(message = "El nombre no puede ser nulo")
    private String name;

    //@NotNull(message = "Debe tener una descripcion")
    private String description;

    //@NotNull(message = "Debe tener una imagen")
    private String image;

    @CreationTimestamp
    private Timestamp timestamps;

}
