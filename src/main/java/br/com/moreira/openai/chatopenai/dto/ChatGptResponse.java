package br.com.moreira.openai.chatopenai.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {

    private List<Choice> choices;

    @Data
    public static class Choice {
        private int index;
        private Message message;
    }
}
