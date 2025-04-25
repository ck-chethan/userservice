package org.lld.userservice.services;

import org.lld.userservice.models.Token;
import org.lld.userservice.models.User;

public interface UserService {
    Token login(String username, String password);
    User signup(String email, String password, String name);
    void logout(String token);
}
