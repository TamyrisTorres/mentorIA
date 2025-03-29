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

import com.TTecnologia.mentorIA.dao.UsuarioDao;
import com.TTecnologia.mentorIA.dto.RegisterRequestDTO;
import com.TTecnologia.mentorIA.dto.ResponseDTO;
import com.TTecnologia.mentorIA.model.entity.Usuario;
import com.TTecnologia.mentorIA.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/createUser")
public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;
    private UsuarioDao usuarioDao;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){

        Optional<Usuario> usuario = this.usuarioDao.findByEmail(body.email());

        if (usuario.isEmpty()){
            Usuario newUsuario = new Usuario();
            newUsuario.setNome(body.nome());
            newUsuario.setEmail(body.email());
            newUsuario.setSenha(passwordEncoder.encode(body.password()));

            this.usuarioDao.save(newUsuario);

            String token = newUsuario.getSenha();
            return ResponseEntity.ok(new ResponseDTO(newUsuario.getNome()));
        }

        return ResponseEntity.badRequest().build();
    }
}
