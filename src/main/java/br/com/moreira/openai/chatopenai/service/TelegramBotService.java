package br.com.moreira.openai.chatopenai.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Interface que define um serviço de bot do Telegram.
 */
public interface TelegramBotService {

    /**
     * Manipula as atualizações recebidas do Telegram.
     *
     * @param update A atualização recebida do Telegram.
     */
    void onUpdateReceived(Update update);

    /**
     * Retorna o nome de usuário do bot.
     *
     * @return O nome de usuário do bot.
     */
    String getBotUsername();

    /**
     * Retorna o token de autenticação do bot.
     *
     * @return O token de autenticação do bot.
     */
    String getBotToken();

    /**
     * Executa a inicialização do bot após a injeção de dependências.
     *
     * @throws Exception Se ocorrer um erro durante a inicialização.
     */
    void afterPropertiesSet() throws Exception;
}
