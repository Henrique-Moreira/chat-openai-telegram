package br.com.moreira.openai.chatopenai.service;

import java.util.List;

import br.com.moreira.openai.chatopenai.dto.ChatGptResponse;
import br.com.moreira.openai.chatopenai.dto.Message;

/**
 * Esta interface representa um serviço de chat para interagir com um chatbot baseado em GPT.
 */
public interface OpenAiService {
    
    /**
     * Inicia uma conversa de chat com o chatbot usando uma lista de mensagens e recebe uma resposta.
     *
     * @param messages Uma lista de mensagens trocadas na conversa de chat.
     * @return Um {@link ChatGptResponse} contendo a resposta do chatbot.
     */
    ChatGptResponse chat(List<Message> messages);
}

