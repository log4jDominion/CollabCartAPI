package com.collab.cart.collabcartfinal.controller;

import com.collab.cart.collabcartfinal.models.UserModel;
import com.collab.cart.collabcartfinal.services.AuthService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserModel userModel) throws IOException {
        return new ResponseEntity<>(authService.registerUser(userModel), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserModel> validateLogin(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(authService.validateLogin(userModel.emailId, userModel.password), HttpStatus.OK);
    }
}
