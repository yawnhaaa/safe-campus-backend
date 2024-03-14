package com.safe.safecampusbackend.util.result;

import lombok.Getter;

public class ResultUtil {
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    @Getter
    public enum ErrorCode {
        // 定义常见的错误码和消息
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found");

        private final int code;
        private final String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}
