package com.acupoint.controller;

import com.acupoint.respository.ChatHistoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequestMapping("/testAi")
public class testController {

    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;
    @Autowired
    private ChatClient dashScopeChatClient;

    @RequestMapping("/profundityChat")
    public Flux<ChatResponse> profundityChat(@RequestParam("prompt") String prompt,
                                             @RequestParam("chatId") String chatId) {
        //1.保存会话id
        chatHistoryRepository.save("testChat", chatId);
        Flux<ChatResponse> chatResponse = dashScopeChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .chatResponse();

        chatResponse.subscribe(chatResponse1 -> {
            Map<String, Object> metadata = chatResponse1.getResults().getFirst().getOutput().getMetadata();
            String think = metadata.get("reasoningContent").toString();
            System.out.println(think);
        });
        return chatResponse;
    }

    private Flux<String> textChat2(String prompt, String chatId) {
        Flux<ChatResponse> chatResponse = dashScopeChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .chatResponse();

        return chatResponse.flatMap(chatResponse1 -> {
            Map<String, Object> metadata = chatResponse1.getResults().getFirst().getOutput().getMetadata();
            String think = metadata.get("reasoningContent").toString();
            if (!(think == null || think.isEmpty())) {
                return Flux.just("<think>" + think);
            } else {
                String text = chatResponse1.getResults().getFirst().getOutput().getText();
                return Flux.just(text);
            }
        });
    }
}
