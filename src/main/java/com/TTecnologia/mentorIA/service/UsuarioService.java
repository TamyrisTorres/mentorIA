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

package com.TTecnologia.mentorIA.service;

import com.TTecnologia.mentorIA.dao.UsuarioDao;
import com.TTecnologia.mentorIA.model.entity.Role;
import com.TTecnologia.mentorIA.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    public Usuario addUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    public Optional<Usuario> getUsuario(Integer id) {
        return usuarioDao.findById(id);
    }

    public List<Usuario> getAllUsuario() {
        return usuarioDao.findAll();
    }

    public Usuario updateUsuario(Integer id, Usuario newUsuario) {
        Optional<Usuario> usuarioOptional = getUsuario(id);

        Usuario usuarioOld = usuarioOptional.get();

        usuarioOld.setNome(newUsuario.getNome());
        usuarioOld.setCpf(newUsuario.getCpf());
        usuarioOld.setEmail(newUsuario.getEmail());
        usuarioOld.setStatus(newUsuario.getStatus());
        usuarioOld.setDataCadastro(newUsuario.getDataCadastro());

        return usuarioDao.save(usuarioOld);
    }

    public void deleteUsuario(Integer id) {
        usuarioDao.deleteById(id);
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().stream().map(Role::getNome).toArray(String[]::new))
                .build();
    }
}
