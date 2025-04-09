package com.acupoint.entity.po;

public class HistoryRepository {

    private String chatId;
    private String title;

    public HistoryRepository(String chatId, String title) {
        this.chatId = chatId;
        this.title = title;
    }

    public HistoryRepository() {
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
