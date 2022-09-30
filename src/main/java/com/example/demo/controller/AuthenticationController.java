package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@RestController
@RequestMapping("")
public class AuthenticationController {

    @PostMapping("auth")
    public void login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        System.out.println(loginDTO.toString());
    }
//    @PostMapping("login")
//    public void login1(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
//        System.out.println(loginDTO.toString());
//    }

}
