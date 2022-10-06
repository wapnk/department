package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@RestController
@RequestMapping("")
public class AuthenticationController {

    @PostMapping("auth")
    public void login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
