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

import com.TTecnologia.mentorIA.dao.MentoriaDao;
import com.TTecnologia.mentorIA.model.entity.Mentoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentoriaService {

    private MentoriaDao mentoriaDao;

    public Mentoria addMentoria(Mentoria mentoria) {
        return mentoriaDao.save(mentoria);
    }

    public Optional<Mentoria> getMentoria(Integer id) {
        return mentoriaDao.findById(id);
    }

    public List<Mentoria> getAllMentoria() {
        return mentoriaDao.findAll();
    }

    public Mentoria updateMentoria(Integer id, Mentoria newMentoria) {
        Optional<Mentoria> mentoriaOptional = getMentoria(id);

        Mentoria mentoriaOld = mentoriaOptional.get();

        mentoriaOld.setUsuario(newMentoria.getUsuario());
        mentoriaOld.setDataInicial(newMentoria.getDataInicial());
        mentoriaOld.setPlanoDesenvolvimento(newMentoria.getPlanoDesenvolvimento());
        mentoriaOld.setPontosMentoria(newMentoria.getPontosMentoria());
        mentoriaOld.setPrazoEncerramento(newMentoria.getPrazoEncerramento());

        return mentoriaDao.save(mentoriaOld);
    }

    public void deleteMentoria(Integer id) {
        mentoriaDao.deleteById(id);
    }
}
