package org.lld.userservice.services;

import org.lld.userservice.models.User;

public interface UserService {
    User login(String username, String password);
    User signUp(String email, String password, String name);
    void logOut(String token);
}
