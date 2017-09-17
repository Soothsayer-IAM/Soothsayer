package com.soothsayer.authn.service.authn;

import coms.soothsayer.core.entities.user.UserEntity;

public interface Authenticator<T> {
    UserEntity authn(T credential);
}
