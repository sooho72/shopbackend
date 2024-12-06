package com.pgm.shopserver.service;

import com.pgm.shopserver.domain.User;

public interface AuthenticationService {
    public User signInAndReturnJWT(User sinInRequest);
}
