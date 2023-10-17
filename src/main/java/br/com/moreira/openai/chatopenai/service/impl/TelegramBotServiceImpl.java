package br.com.moreira.openai.chatopenai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import br.com.moreira.openai.chatopenai.dto.ChatGptResponse;
import br.com.moreira.openai.chatopenai.dto.ChatGptResponse.Choice;
import br.com.moreira.openai.chatopenai.dto.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings("deprecation")
public class TelegramBotServiceImpl extends TelegramLongPollingBot implements InitializingBean {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Autowired
    private OpenAiServiceImpl openAiService;

    // Cria uma lista de mensagens da conversa
    private List<Message> mensagens = new ArrayList<>();

    public TelegramBotServiceImpl() {
        // Inicializa a lista de mensagens com a mensagem "system: prompt"
        Message systemMessage = new Message("system", "Você deve responder sempre em formato json");
        mensagens.add(systemMessage);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var mensagem = responder(update);
            try {
                execute(mensagem);
            } catch (TelegramApiException e) {
                log.error("Erro ao enviar a mensagem", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private SendMessage responder(Update update) {
        // Obtém a mensagem recebida e o chat ID
        String msgRecebida = update.getMessage().getText().toLowerCase();
        String chatId = update.getMessage().getChatId().toString();

        // Registra a mensagem recebida no log
        log.info("mensagem recebida -> {}", msgRecebida);

        // Cria a mensagem do usuário e a adiciona à lista de mensagens
        Message userMessage = new Message("user", msgRecebida);
        mensagens.add(userMessage);

        // Chama o chat GPT com a lista de mensagens
        ChatGptResponse gptResponse = openAiService.chat(mensagens);

        // Obtém a última mensagem do GPT(resposta)
        Choice ultimaMsgGPT = gptResponse.getChoices().get(gptResponse.getChoices().size() - 1);

        // Adiciona a mensagem do GPT à lista de mensagens da interação
        mensagens.add(ultimaMsgGPT.getMessage());

        // Obtém a resposta do GPT
        String resposta = ultimaMsgGPT.getMessage().getContent();

        // Registra a mensagem enviada no log
        log.info("mensagem enviada -> {}", resposta);

        // Cria e retorna a mensagem de resposta
        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Ocorreu um erro ao registrar o bot.", e);
        }
    }
}