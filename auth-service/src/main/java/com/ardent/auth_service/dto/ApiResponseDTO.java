package com.ardent.auth_service.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ApiResponseDTO<T> {

    Boolean success;
    String message;
    T data;
}
