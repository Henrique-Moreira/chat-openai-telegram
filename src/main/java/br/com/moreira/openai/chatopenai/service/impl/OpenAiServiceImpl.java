package br.com.moreira.openai.chatopenai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.moreira.openai.chatopenai.dto.ChatGPTRequest;
import br.com.moreira.openai.chatopenai.dto.ChatGptResponse;
import br.com.moreira.openai.chatopenai.dto.Message;
import br.com.moreira.openai.chatopenai.service.OpenAiService;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.api.key}")
    private String apiKey;

    public ChatGptResponse chat(List<Message> messages) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ChatGPTRequest request = new ChatGPTRequest(model, messages);

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        try {
            return restTemplate.postForObject(apiURL, entity, ChatGptResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
