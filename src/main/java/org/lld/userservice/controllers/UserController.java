package org.lld.userservice.controllers;

import org.antlr.v4.runtime.misc.NotNull;
import org.lld.userservice.dtos.*;
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
     public ResponseEntity<LoginDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(LoginDto.from(userService.login(loginRequestDto.getEmail(),  loginRequestDto.getPassword())));
     }

     @PostMapping("/signup")
     public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        return ResponseEntity.ok(UserDto.from(userService.signup(email, password, name)));
     }

     @PostMapping("/logout")
     public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logout(logoutRequestDto.getToken());
        return ResponseEntity.ok().build();
     }
     @PostMapping("/validate/{token}")
     public ResponseEntity<UserDto> validateToken(@PathVariable("token") String token) {
        return ResponseEntity.ok(UserDto.from(userService.validateToken(token)));
     }
}
