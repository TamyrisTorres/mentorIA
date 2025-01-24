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

import com.TTecnologia.mentorIA.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos.")
    private String cpf;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 8, max = 10, message = "A senha deve ter entre 8 e 10 caracteres.")
    private String senha;

    public @Size(min = 8, max = 10, message = "A senha deve ter entre 8 e 10 caracteres.") String getSenha() {
        return senha;
    }

    private LocalDateTime dataCadastro;
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mentoria_id", referencedColumnName = "id")
    private Mentoria mentoria;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public Usuario(String nome, String cpf, String email, Status status, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSenha(@Size(min = 8, max = 10, message = "A senha deve ter entre 8 e 10 caracteres.") String senha) {
        this.senha = senha;
    }

    public Mentoria getMentoria() { return mentoria;}

    public void setMentoria(Mentoria mentoria) { this.mentoria = mentoria;}

    public List<Feedback> getFeedbacks() { return feedbacks;}

    public void setFeedbacks(List<Feedback> feedbacks) { this.feedbacks = feedbacks;}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + ", email='" + email + '\'' + ", dataCadastro=" + dataCadastro + ", status=" + status + '}';
    }
}
