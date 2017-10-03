package com.soothsayer.core.error.exception;

import com.soothsayer.core.error.ErrorCode;

public class SoothSayerException extends RuntimeException {

    private ErrorCode errorCode;

    public SoothSayerException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public SoothSayerException(ErrorCode errorCode, Throwable ex) {
        super(ex);
        this.errorCode = errorCode;
    }
}
