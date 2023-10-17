# Integração de Bot Telegram com OpenAI GPT

## Visão Geral

Este aplicativo Spring Boot foi desenvolvido para se conectar ao serviço de Bot do Telegram e utilizar o assistente baseado na tecnologia OpenAI GPT para interações inteligentes com chatbots. Ele fornece dois principais serviços:

1. **Serviço OpenAI**: Este serviço permite interações com um chatbot alimentado pelo modelo OpenAI GPT. Os usuários podem iniciar conversas com o chatbot trocando mensagens e recebendo respostas. As mensagens do chat são processadas, e as respostas são geradas usando o modelo GPT da OpenAI.

2. **Serviço Bot Telegram**: Este serviço é responsável pela integração com a plataforma de Bots do Telegram. Ele lida com as atualizações recebidas do Telegram, permitindo que o aplicativo atue como um chatbot do Telegram. O bot pode receber mensagens dos usuários do Telegram e fornecer respostas.

## Serviço OpenAI

### Interface: `OpenAiService`

Este serviço representa a funcionalidade de chatbot alimentada pela tecnologia OpenAI GPT.

#### Métodos:

- `ChatGptResponse chat(List<Message> messages)`: Inicia uma conversa com o chatbot usando uma lista de mensagens e retorna uma resposta. A resposta é encapsulada em um objeto `ChatGptResponse`.

## Serviço Bot Telegram

### Interface: `TelegramBotService`

Este serviço define a funcionalidade principal de integração com a plataforma do Bot Telegram.

#### Métodos:

- `void onUpdateReceived(Update update)`: Lida com as atualizações recebidas da plataforma Telegram, permitindo que o bot responda às mensagens e eventos dos usuários.

- `String getBotUsername()`: Retorna o nome de usuário do bot na plataforma do Telegram.

- `String getBotToken()`: Retorna o token de autenticação do bot na plataforma do Telegram.

- `void afterPropertiesSet() throws Exception`: Executa a inicialização do bot após a injeção de dependências. Qualquer configuração ou ajuste necessário deve ser realizado neste método.

## Uso

Este aplicativo é destinado a ser configurado como um bot do Telegram que se conecta ao modelo OpenAI GPT para funcionalidades de chatbot. Você pode implementar classes concretas que sigam as interfaces `OpenAiService` e `TelegramBotService` para personalizar o comportamento do seu bot e das interações do chat.

Certifique-se de obter as credenciais de API necessárias tanto para o Bot do Telegram quanto para o OpenAI, e configure-as nas propriedades do aplicativo.

## Dependências

Certifique-se de adicionar as dependências necessárias no seu arquivo `pom.xml`:

```xml
<!-- Spring Boot Starter Actuator (Monitoramento de aplicação) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- Spring Boot Starter Web (Recursos Web e API) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Project Lombok (Aprimoramento de produtividade para Java) -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Telegram Bots Library (Biblioteca para interagir com a plataforma do Telegram) -->
<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots</artifactId>
    <version>6.8.0</version>
</dependency>
```

## Configuração

Configure as propriedades do seu aplicativo com as configurações necessárias, incluindo o token do bot do Telegram e as credenciais do OpenAI GPT.

```properties
# Configuração do Bot Telegram
telegram.bot.token=SEU_TOKEN_DO_BOT_DO_TELEGRAM
telegram.bot.username=SEU_USERNAME_DO_BOT_DO_TELEGRAM

# Configuração do OpenAI GPT
openai.api.key=SUA_CHAVE_DE_API_DO_OPENAI
```

## Autor
* Henrique Moreira Amorim
* henriquemoreiraa@gmail.com

