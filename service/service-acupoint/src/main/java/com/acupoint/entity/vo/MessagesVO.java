package com.acupoint.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

//@Data
//@NoArgsConstructor
public class MessagesVO {
    private String role;
    private String content;

    public MessagesVO() {
    }

    public MessagesVO(Message message) {
        //从message中获取类型
        switch (message.getMessageType()) {
            case USER:
                role = "user";
                break;
            case ASSISTANT:
                role = "assistant";
                break;
            default:
                role = "";
                break;
        }
        //从message中获取内容
        this.content = message.getText();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
