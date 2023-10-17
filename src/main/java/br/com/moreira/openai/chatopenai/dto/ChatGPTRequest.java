package br.com.moreira.openai.chatopenai.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = new ArrayList<>();

        for (int i = 0; i < messages.size(); i++) {
            this.messages.add(i, messages.get(i));
        }
    }
}
