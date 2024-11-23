package com.marceloluiz.DSCommerce.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

//Lombok provides an annotation called @SuperBuilder for classes with inheritance.
//This ensures compatibility between parent and child class builders.
@SuperBuilder
@Getter
public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
