package com.soothsayer.authn.service.authn;

import com.soothsayer.core.entities.user.UserEntity;

public interface Authenticator<T> {
    UserEntity authn(T credential);
}
