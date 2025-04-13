package com.acupoint.entity.vo;

import com.acupoint.entity.po.ReasoningContentMessage;

import java.util.List;

public class ReasoningContentMessageVO {
    private String currentChatId;
    private List<ReasoningContentMessage> messages;

    public ReasoningContentMessageVO(String currentChatId, List<ReasoningContentMessage> messages) {
        this.currentChatId = currentChatId;
        this.messages = messages;
    }

    public ReasoningContentMessageVO() {
    }

    public String getCurrentChatId() {
        return currentChatId;
    }

    public void setCurrentChatId(String currentChatId) {
        this.currentChatId = currentChatId;
    }

    public List<ReasoningContentMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ReasoningContentMessage> messages) {
        this.messages = messages;
    }
}
