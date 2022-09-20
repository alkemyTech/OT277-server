package com.alkemy.ong.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @ApiModelProperty(example = "40# or 500", position = 1)
    private int status;
    @ApiModelProperty(example = " {\n" +
            "errorField: description" +
            "\n}", position = 2)
    private List<String> errors;

    @JsonIgnore
    private Timestamp timestamp;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public void add(String message) {
        this.errors.add(message);
    }
}
