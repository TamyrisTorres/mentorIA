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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DeepSeekService {
    private final String model = "deepseek-r1:1.5b";


    public String getResponse(String message) throws IOException {
        String fullResponse = "";

        String prompt = """
                Me ajude a criar uma lista de informações sobre a carreira profissional de %s, no mercado brasileiro. 
                Por favor, responda em português do Brasil.
                """.replace("%s",message);

        try {
            // Set up an HTTP POST request
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create request JSON
            JSONObject requestJson = new JSONObject();
            requestJson.put("model", model);
            requestJson.put("prompt", prompt);
            requestJson.put("stream", false);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestJson.toString().getBytes());
            }

            // Get response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String responseLine = br.readLine();
                if (responseLine != null) {
                    JSONObject responseJson = new JSONObject(responseLine);
                    fullResponse = responseJson.getString("response");

                    Pattern pattern = Pattern.compile("\\<think\\>(.*?)\\<\\/think\\>", Pattern.DOTALL);
                    Matcher matcher = pattern.matcher(fullResponse);
                    fullResponse = matcher.replaceAll("");

                }
            }

            //conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error communicating with DeepSeek API", e);
        }

        return fullResponse;
    }
}