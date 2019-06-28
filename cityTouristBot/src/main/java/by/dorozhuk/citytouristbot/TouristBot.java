package by.dorozhuk.citytouristbot;

import by.dorozhuk.citytouristbot.service.CityService;
import by.dorozhuk.citytouristbot.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TouristBot extends TelegramLongPollingBot {

    @Autowired
    CityService cityService;

    @Autowired
    InfoService infoService;

    private boolean newCity = false;
    private boolean deleteCity = false;
    private boolean newInfoCIty = false;
    private String cityNameForInfo = "";
    private boolean newInfo = false;


    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        try {

            setButtons(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/cities"));
        keyboardFirstRow.add(new KeyboardButton("/addCity"));
        keyboardFirstRow.add(new KeyboardButton("/deleteCity"));
        keyboardFirstRow.add(new KeyboardButton("/addInfo"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            if (newCity) {
                sendMsg(message, cityService.saveCity(message.getText()));
                newCity = false;
            }
            if (deleteCity) {
                sendMsg(message, cityService.deleteCity(message.getText()));
                deleteCity = false;
            }
            if (newInfo) {
                sendMsg(message, infoService.saveInfo(cityNameForInfo, message.getText()));
                newInfo = false;
            }
            if (newInfoCIty) {
                cityNameForInfo = message.getText();
                sendMsg(message, "Input info.");
                newInfoCIty = false;
                newInfo = true;
            }

            switch (message.getText()) {
                case "/cities":
                    sendMsg(message, cityService.getCities());
                    break;
                case "/addCity":
                    sendMsg(message, "Input new City name.");
                    newCity = true;
                    break;
                case "/deleteCity":
                    sendMsg(message, "Input City name.");
                    deleteCity = true;
                    break;
                case "/addInfo":
                    sendMsg(message, "Input City name.");
                    sendMsg(message, cityService.getCities());
                    newInfoCIty = true;
                    break;
            }

            if (!newInfo && !newCity && !newInfoCIty && !deleteCity) {
                sendMsg(message, infoService.findInfosByCity(message.getText()));
            }

        }
    }

    public String getBotUsername() {
        return "city_tourist_bot";
    }

    public String getBotToken() {
        return "871852957:AAExBV1bUEl_MEbf9MG4Gfm5tXTNjI3S6O0";
    }
}
