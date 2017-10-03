package com.soothsayer.authn.cas.controller.exception;

import com.soothsayer.core.error.ErrorCode;
import com.soothsayer.core.error.exception.SoothSayerException;

public class CASAppNotRegisterException extends SoothSayerException {

    public CASAppNotRegisterException() {
        super(ErrorCode.CAS_APP_NOT_REGISTER);
    }

}
