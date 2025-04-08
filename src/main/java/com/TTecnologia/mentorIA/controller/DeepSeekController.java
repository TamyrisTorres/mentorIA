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

import com.TTecnologia.mentorIA.dto.QuestionRequestDTO;
import com.TTecnologia.mentorIA.dto.QuestionResponseDTO;
import com.TTecnologia.mentorIA.service.IaService.DeepSeekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/ia")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/ask")
    public ResponseEntity askQuestion(@RequestBody QuestionRequestDTO request) {
        String paramQuestion = request.question();

            if (paramQuestion == null) {
                return ResponseEntity.badRequest().build();
            }
            try {
                String response = deepSeekService.getResponse(paramQuestion);
                return ResponseEntity.ok(new QuestionResponseDTO(response));
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }

    }
}
