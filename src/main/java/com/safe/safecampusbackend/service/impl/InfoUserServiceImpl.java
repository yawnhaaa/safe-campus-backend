package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.InfoUserDAO;
import com.safe.safecampusbackend.service.InfoUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InfoUserServiceImpl implements InfoUserService {
    private final InfoUserDAO infoUserDAO;
}
