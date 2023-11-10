package com.example.spring_security.controller;

import com.example.spring_security.entity.User;
import com.example.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/livreUm")
    public String rotaLivre(){
        return "<h1>ROTA LIVRE 01</h1>";
    }

    @PostMapping("/livreDois")
    public String rotelivreteste(){
        return "<h1>ROTA LIVRE 02</h1>";
    }

    @GetMapping("/userAuth")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String rotaUser(){
        return "<h1>ROTA DE USUÁRIO COM AUTENTICAÇÃO</h1>";
    }

    @GetMapping("/adminAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String rotaAdmin(){
        return "<h1>ROTA DE USUÁRIO COM ATENTICAÇÃO</h1>";
    }


    @PostMapping("/registro")
    public ResponseEntity<HttpStatus> cadastrarUser(@RequestBody User user){
        try{
            userService.registrar(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
