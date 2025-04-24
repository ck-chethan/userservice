package org.lld.userservice.controllers;

import org.lld.userservice.dtos.SignUpRequestDto;
import org.lld.userservice.models.User;
import org.lld.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired // In controller Autowired is optional.
     private UserController(UserService userService) {
         this.userService = userService;
     }

     public ResponseEntity<User> login(String email, String password) {
         return ResponseEntity.ok(userService.login(email, password));
     }

     @PostMapping("/")
     public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        return ResponseEntity.ok(userService.signUp(email, password, name));
     }

     public ResponseEntity<Void> logOut(String token) {
         return null;
     }
}
