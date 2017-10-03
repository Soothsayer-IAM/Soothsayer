package com.soothsayer.core.error;

public enum ErrorCode {

    //CAS Error code
    CAS_APP_NOT_REGISTER("SoothSayer-CAS-ERROR-0001", "CAS应用未注册");

    private String errorCode;
    private String message;

    ErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
