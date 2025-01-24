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

import com.TTecnologia.mentorIA.model.entity.Mentoria;
import com.TTecnologia.mentorIA.service.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mentoria")
public class MentoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @PostMapping
    public Mentoria addMentoria(@RequestBody Mentoria mentoria) {
        return mentoriaService.addMentoria(mentoria);
    }

    @GetMapping
    public List<Mentoria> getAllMentoria() {
        return mentoriaService.getAllMentoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentoria> getMentoria(@PathVariable Integer id) {
        Optional<Mentoria> mentoria = mentoriaService.getMentoria(id);

        if (mentoria.isPresent()) {
            return ResponseEntity.ok(mentoria.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("mentoria/{id}")
    public ResponseEntity<Mentoria> updateMentoria(@PathVariable Integer id, Mentoria newMentoria) {
        Mentoria mentoria = mentoriaService.updateMentoria(id, newMentoria);

        if (mentoria != null) {
            return ResponseEntity.ok(mentoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMentoria(@PathVariable Integer id) {
        mentoriaService.deleteMentoria(id);

        if (mentoriaService.getMentoria(id).isEmpty()) {
            System.out.println("Mentoria deletado com sucesso!");
        }
    }
}

