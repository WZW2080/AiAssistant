package com.acupoint.controller;

import com.acupoint.entity.po.HistoryRepository;
import com.acupoint.entity.vo.MessagesVO;
import com.acupoint.respository.ChatHistoryRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai/history")
public class ChatHistoryController {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Autowired
    private ChatMemory chatMemory;

    //获取聊天记录id和标题
    @GetMapping("/{type}")
    public List<HistoryRepository> getChatIds(@PathVariable("type") String type) {
        return chatHistoryRepository.getChatIds(type);
    }

    //获取指定会话id的聊天记录
    @GetMapping("/{type}/{chatId}")
    public List<Map<String, Object>> getChatHistory(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        List<Message> messages = chatMemory.get(chatId, Integer.MAX_VALUE);

        if (messages == null) {
            return List.of();
        }
        List<MessagesVO> messagesVOList = messages.stream().map(MessagesVO::new).toList();
        return List.of(Map.of("currentChatId", chatId, "messages",messagesVOList));
    }

    @PutMapping("/{type}")
    public void updateChatHistoryTitle(@PathVariable("type") String type, @RequestBody HistoryRepository historyRepository) {
        chatHistoryRepository.updateTitle(type, historyRepository);
    }

    //根据会话id删除聊天记录
    @DeleteMapping("/{type}/{chatId}")
    public void deleteChatHistory(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        chatHistoryRepository.delete(type, chatId);
    }
}
