package br.com.moreira.openai.chatopenai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private String role;
    private String content;//prompt
}

