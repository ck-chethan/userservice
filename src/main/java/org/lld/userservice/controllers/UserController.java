package org.lld.userservice.controllers;

import org.antlr.v4.runtime.misc.NotNull;
import org.lld.userservice.dtos.LoginRequestDto;
import org.lld.userservice.dtos.LogoutRequestDto;
import org.lld.userservice.dtos.SignUpRequestDto;
import org.lld.userservice.models.Token;
import org.lld.userservice.models.User;
import org.lld.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired // In controller Autowired is optional.
     private UserController(UserService userService) {
         this.userService = userService;
     }

     @PostMapping("/login")
     public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.login(loginRequestDto.getEmail(),  loginRequestDto.getPassword()));
     }

     @PostMapping("/signup")
     public ResponseEntity<User> signup(@RequestBody SignUpRequestDto request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        return ResponseEntity.ok(userService.signup(email, password, name));
     }

     @PostMapping("/logout")
     public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logout(logoutRequestDto.getToken());
        return ResponseEntity.ok().build();
     }
     @PostMapping("/validate/{token}")
     public ResponseEntity<User> validateToken(@PathVariable("token") String token) {
        return ResponseEntity.ok(userService.validateToken(token));
     }
}
