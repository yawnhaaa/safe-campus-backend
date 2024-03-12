package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.entity.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getUser();
}
