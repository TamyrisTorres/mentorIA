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

import com.TTecnologia.mentorIA.model.entity.Usuario;
import com.TTecnologia.mentorIA.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }


    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.getUsuario(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, Usuario newUsuario) {
        Usuario usuario = usuarioService.updateUsuario(id, newUsuario);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);

        if (usuarioService.getUsuario(id).isEmpty()) {
            System.out.println("Usuario deletado com sucesso!");
        }
    }
}
