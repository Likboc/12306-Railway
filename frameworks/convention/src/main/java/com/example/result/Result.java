package com.example.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
public class Result<T> {
    private T Data;

    public static String SUCCESS_CODE = "0";

    private int Status;

    private String code;

    private String message;

    private String requestId;

    public boolean isSucceed() {
        return Objects.equals(code, SUCCESS_CODE);
    }
}
