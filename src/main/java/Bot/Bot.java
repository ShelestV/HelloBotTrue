package Bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi tBotApi = new TelegramBotsApi();

        try {
            tBotApi.registerBot(new Bot());
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMsg = new SendMessage();
        sendMsg.enableMarkdown(true);
        sendMsg.setChatId(message.getChatId());
        sendMsg.setReplyToMessageId(message.getMessageId());
        sendMsg.setText(text);
        try {
            sendMessage(sendMsg);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Getting message
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Я могу только здороваться!\n" +
                            "Мои функции:\n" +
                            "/help - помощь\n" +
                            "/hello - приветствие");
                    break;
                case "/hello":
                    sendMsg(message, "Привет!");
                    break;
                default:

            }
        }
    }

    @Override
    public String getBotUsername() {
        return "HelloBotHello";
    }

    @Override
    public String getBotToken() {
        return "1616231971:AAE6Y2tBmpOxApTZnK-eYKsCJ51cinefiiY";
    }
}
