package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private HttpStatus status;
    private Map<String,String> errors;
}
