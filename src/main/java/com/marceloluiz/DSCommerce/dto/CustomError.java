package com.marceloluiz.DSCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
