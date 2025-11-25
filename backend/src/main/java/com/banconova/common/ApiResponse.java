package com.banconova.common;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiResponse<T> {
    private boolean ok;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data, String message){
        return ApiResponse.<T>builder().ok(true).message(message).data(data).build();
    }

    public static <T> ApiResponse<T> ok(T data){
        return ok(data, "OK");
    }

    public static <T> ApiResponse<T> fail(String message){
        return ApiResponse.<T>builder().ok(false).message(message).build();
    }
}
