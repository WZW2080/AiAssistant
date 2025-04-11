package com.acupoint.entity.vo;

import java.util.List;

public class MessageHistoryVO {

    private String type;
    private String chatId;
    private List<MessagesVO> messages;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public List<MessagesVO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesVO> messages) {
        this.messages = messages;
    }
}
