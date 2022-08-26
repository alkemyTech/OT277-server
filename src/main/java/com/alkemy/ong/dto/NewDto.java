package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.security.Timestamp;


@Getter
@Setter
public class NewDto {
    @NotNull(message = "Nombre no puede estar vacio")
    private String name;

    @NotNull(message = "El contenido no puede estar vacio")
    private String content;

    @NotNull(message = "La imagen no puede estar vacio")
    private String image;

    private CategoryDto categoryDto;

    @CreationTimestamp
    private Timestamp timestamps;

    @NotNull(message = "Debe tener un tipo")
    private String type;
}
