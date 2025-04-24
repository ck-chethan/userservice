package org.lld.userservice.services;

import org.lld.userservice.models.User;
import org.lld.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SelfUserService implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public SelfUserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        // Implement login logic here
        return null;
    }

    @Override
    public User signUp(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        user.setName(name);
        return userRepository.save(user);
    }

    @Override
    public void logOut(String token) {
        // Implement logout logic here
    }
}
