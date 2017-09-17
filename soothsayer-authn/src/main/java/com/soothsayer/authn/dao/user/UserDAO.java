package com.soothsayer.authn.dao.user;

import coms.soothsayer.core.entities.user.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends  MongoRepository<UserEntity, String>{
    UserEntity findByUserName(String userName);
}
