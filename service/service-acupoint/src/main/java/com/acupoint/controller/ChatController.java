package com.acupoint.controller;

import com.acupoint.respository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.*;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class ChatController {
    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8") // 解决乱码问题
    public Flux<String> chat(@RequestParam("prompt") String prompt,
                             @RequestParam("chatId") String chatId,
                             @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        //1.保存会话id
        chatHistoryRepository.save("chat", chatId);
        //2.请求模型
        //判断文件是否有值
        if (files == null || files.isEmpty()) {
            //没有文件 普通聊天
            return textChat(prompt, chatId);
        } else {
            //有文件，多模态聊天
            return multiModalChat(prompt, chatId, files);
        }
    }

    //带文件聊天
    private Flux<String> multiModalChat(String prompt, String chatId, List<MultipartFile> files) {
        //构建多模态数据
        Media[] medias = files.stream().map(file -> new Media(
                        MediaType.valueOf(Objects.requireNonNull(file.getContentType())), file.getResource()))
                .toArray(Media[]::new);


        return chatClient
                .prompt()
                .user(c -> c.text(prompt).media(medias))
                .advisors(advisorSpec -> {
                    advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId);
                })
                .stream()//流式输出
                .content();
    }

    //普通聊天
    private Flux<String> textChat(String prompt, String chatId) {
        return chatClient
                .prompt()
                .advisors(advisorSpec -> {
                    advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId);
                })
                .user(prompt)
                .stream()//流式输出
                .content();
    }
}
