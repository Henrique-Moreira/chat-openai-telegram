package br.com.moreira.openai.chatopenai.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;
}