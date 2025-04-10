/*
 * Copyright © 2025 Tamyris Torres. All rights reserved.
 *
 * This software and its associated documentation are the exclusive property of Tamyris Torres.
 *  Reproduction, distribution, modification, or unauthorized use of this system is
 *  strictly prohibited without prior written consent from the author or owner.
 *  The use of this software is granted under a limited end-user license and is subject
 * to the terms and conditions specified in the license agreement. This software is intended
 *  solely for lawful purposes and in compliance with applicable laws.
 * Any violation of these terms may result in legal penalties, including but not limited to
 * civil and criminal sanctions. For inquiries or requests, please contact: wedellatorres@gmail.com.
 */

package com.TTecnologia.mentorIA.controller;

import com.TTecnologia.mentorIA.config.JwtAuthFilter;
import com.TTecnologia.mentorIA.config.SecurityConfig;
import com.TTecnologia.mentorIA.dto.ResponseDTO;
import com.TTecnologia.mentorIA.model.entity.Usuario;
import com.TTecnologia.mentorIA.service.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTecnologia.mentorIA.dao.UsuarioDao;
import com.TTecnologia.mentorIA.dto.LoginRequestDTO;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioDao usuarioDao;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.usuarioDao.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (passwordEncoder.matches(body.password(), usuario.getSenha())){

            String token = this.jwtService.generateToken(usuario);

            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
