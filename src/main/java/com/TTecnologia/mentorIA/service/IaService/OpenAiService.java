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

package com.TTecnologia.mentorIA.service.IaService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}") // Certifique-se de que esta chave está definida no application.properties
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chat(String userMessage) {
        // Constrói o prompt dinâmico com base na pergunta do usuário
        String prompt = """
            Você é um assistente especializado em orientação profissional. Seu objetivo é fornecer informações detalhadas sobre a profissão mencionada pelo usuário.

            O usuário quer conhecer mais sobre a profissão de %s. Com base nisso, forneça uma resposta estruturada incluindo os seguintes tópicos:

            1. **Descrição da profissão**: O que esse profissional faz no dia a dia?
            2. **Habilidades necessárias**: Quais são as principais competências e conhecimentos exigidos?
            3. **Formação e certificações**: É necessário um curso superior ou técnico? Existem certificações importantes?
            4. **Mercado de trabalho**: Como está a demanda para essa profissão atualmente? Existe crescimento na área?
            5. **Faixa salarial**: Qual a média de salário para iniciantes, profissionais experientes e especialistas?
            6. **Desafios da carreira**: Quais são as dificuldades enfrentadas pelos profissionais da área?
            7. **Dicas para iniciantes**: Como alguém pode começar nessa profissão e se destacar?

            Se a profissão mencionada não for clara ou não for reconhecida, peça mais informações ao usuário antes de responder.

            Forneça uma resposta clara, objetiva e motivadora, incentivando o usuário a explorar suas possibilidades.
        """.formatted(userMessage);

        // Corpo da requisição no formato JSON
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", prompt),
                        Map.of("role", "user", "content", userMessage)
                ),
                "temperature", 0.7 // Define a criatividade da resposta
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // Adiciona o token da API

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // Converte a resposta JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            // Verifica se há respostas válidas antes de acessar
            if (root.has("choices") && root.get("choices").isArray() && root.get("choices").size() > 0) {
                return root.get("choices").get(0).get("message").get("content").asText();
            } else {
                return "Nenhuma resposta válida foi retornada pela API.";
            }
        } catch (Exception e) {
            return "Erro ao se conectar com a API: " + e.getMessage();
        }
    }
}
