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

import java.time.LocalDateTime;

@Entity
@Table(name = "mentoria")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "mentoria")
    private Usuario usuario;
    private String planoDesenvolvimento;
    private Long pontosMentoria;
    private LocalDateTime dataInicial;
    private LocalDateTime prazoEncerramento;


    public Mentoria(Usuario usuario, String planoDesenvolvimento, Long pontosMentoria, LocalDateTime dataInicial, LocalDateTime prazoEncerramento) {
        this.usuario = usuario;
        this.planoDesenvolvimento = planoDesenvolvimento;
        this.pontosMentoria = pontosMentoria;
        this.dataInicial = dataInicial;
        this.prazoEncerramento = prazoEncerramento;
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

    public String getPlanoDesenvolvimento() {
        return planoDesenvolvimento;
    }

    public void setPlanoDesenvolvimento(String planoDesenvolvimento) {
        this.planoDesenvolvimento = planoDesenvolvimento;
    }

    public Long getPontosMentoria() {
        return pontosMentoria;
    }

    public void setPontosMentoria(Long pontosMentoria) {
        this.pontosMentoria = pontosMentoria;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getPrazoEncerramento() {
        return prazoEncerramento;
    }

    public void setPrazoEncerramento(LocalDateTime prazoEncerramento) {
        this.prazoEncerramento = prazoEncerramento;
    }

    @Override
    public String toString() {
        return "Mentoria{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", planoDesenvolvimento='" + planoDesenvolvimento + '\'' +
                ", pontosMentoria=" + pontosMentoria +
                ", dataInicial=" + dataInicial +
                ", prazoEncerramento=" + prazoEncerramento +
                '}';
    }
}
