package com.example;

import com.example.result.Result;

/**
 * 全局结果类
 */
public final class Results {
    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setData(data)
                .setCode(Result.SUCCESS_CODE);
    }

    //TODO 返回失败类
    public static <T> Result<T> fail(T data) {
        return new Result<T>()
                .setData(data);
    }
}
