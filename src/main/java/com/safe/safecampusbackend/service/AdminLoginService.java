package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.util.result.Result;

public interface AdminLoginService {
    Result<String> loginGetJWT();
}
