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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chat(String userMessage) {
        // Monta o JSON da requisição
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo"); // Escolha o modelo adequado
        requestBody.put("messages", List.of(
                Map.of("role", "system", "content", "Você é um assistente útil."),
                Map.of("role", "user", "content", userMessage)
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // Adiciona a chave de autenticação

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // Processa a resposta JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            return "Erro ao se conectar com a API: " + e.getMessage();
        }
    }
}
