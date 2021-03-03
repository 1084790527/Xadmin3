package com.yao.service.exception;
/**
 * @author 妖妖
 * @date 17:20 2021/3/2
 */
public class CustException extends RuntimeException {
    private static final long serialVersionUID = 5162710183389028999L;

    public CustException() {
    }

    public CustException(String message) {
        super(message);
    }

    protected CustException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustException(Throwable cause) {
        super(cause);
    }
}
