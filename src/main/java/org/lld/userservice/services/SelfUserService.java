package org.lld.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.lld.userservice.dtos.SendEmailEventDto;
import org.lld.userservice.models.Token;
import org.lld.userservice.models.User;
import org.lld.userservice.repositories.TokenRepository;
import org.lld.userservice.repositories.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class SelfUserService implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public SelfUserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenRepository tokenRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Token login(String username, String password) {
        // Implement login logic here
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getHashedPassword())) {
            Token token = new Token();
            String tokenValue = RandomStringUtils.randomAlphabetic(128);
            token.setValue(tokenValue); // Replace with actual token generation logic
            token.setUser(user.get());
            token.setCreatedAt(user.get().getCreatedAt());
            LocalDate today = LocalDate.now();
            LocalDate thirtyDaysFromNow = today.plusDays(30);
            Date expiryDate = java.sql.Date.valueOf(thirtyDaysFromNow);
            token.setExpiryDate(expiryDate);
            return tokenRepository.save(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public User signup(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        user.setName(name);
        User u = userRepository.save(user);
        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setTo(email);
        sendEmailEventDto.setFrom("chethan@ourservice.com");
        sendEmailEventDto.setSubject("Welcome to our service");
        sendEmailEventDto.setBody("Hello " + name + ",\n\nThank you for signing up!\n\nBest regards,\nThe Team");
        // Send email using Kafka
        try {
            kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(sendEmailEventDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    @Override
    public void logout(String token) {
        Optional<Token> tkn = tokenRepository.findTokenByValueAndIsDeleted(token, false);
        if (tkn.isPresent()) {
            Token tokenToDelete = tkn.get();
            tokenToDelete.setIsDeleted(true);
            tokenRepository.save(tokenToDelete);
        } else {
            throw new RuntimeException("Token not found");
        }
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> tkn = tokenRepository.findTokenByValueAndIsDeletedAndExpiryDateGreaterThan(token, false, new Date());
        if (tkn.isPresent()) {
            return tkn.get().getUser();
        } else {
            throw new RuntimeException("Invalid token");
        }
    }
}
