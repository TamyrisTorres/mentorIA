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

package com.TTecnologia.mentorIA.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String relatorioMentoria;

    public Feedback(Usuario usuario, String relatorioMentoria) {
        this.usuario = usuario;
        this.relatorioMentoria = relatorioMentoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRelatorioMentoria() {
        return relatorioMentoria;
    }

    public void setRelatorioMentoria(String relatorioMentoria) {
        this.relatorioMentoria = relatorioMentoria;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", relatorioMentoria='" + relatorioMentoria + '\'' +
                '}';
    }
}
