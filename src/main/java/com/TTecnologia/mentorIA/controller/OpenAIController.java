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

import com.TTecnologia.mentorIA.model.entity.Feedback;
import com.TTecnologia.mentorIA.model.entity.Usuario;
import com.TTecnologia.mentorIA.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/chat")
public class OpenAIController {

    private final OpenAiService openAiService;
    private FeedbackController feedbackController;

    public OpenAIController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @GetMapping
    public ResponseEntity<String> chat(@RequestParam Usuario usuario, String pergunta) {
        String response = openAiService.chat(pergunta);

        if(!response.isEmpty()){
            feedbackController.addFeedback(
                    new Feedback(
                            usuario, Collections.singletonList(pergunta)));
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().build();
    }
}
