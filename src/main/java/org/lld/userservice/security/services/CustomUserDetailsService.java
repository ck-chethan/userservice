package org.lld.userservice.security.services;

import org.lld.userservice.repositories.UserRepository;
import org.lld.userservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement your logic to load user details from the database or any other source
        // For example:
        Optional<org.lld.userservice.models.User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            CustomUserDetails customUserDetails = new CustomUserDetails(userOptional.get());
            return customUserDetails;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
