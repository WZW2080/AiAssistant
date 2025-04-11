package com.acupoint.controller;

import com.acupoint.entity.vo.MessagesVO;
import com.acupoint.respository.ChatHistoryRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
@Tag(name = "chatAPI", description = "当前版仅测试")
public class ChatController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;
    @Autowired
    private ChatClient dashScopeChatClient;

    private List<MessagesVO> array = new ArrayList<>();

    /**
     * 深度思考会话
     *
     * @param prompt
     * @param chatId
     * @return
     */
    @RequestMapping("/profundityChat")
    public Flux<String> profundityChat(@RequestParam("prompt") String prompt,
                                       @RequestParam("chatId") String chatId,
                                       @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        //1.保存会话id
        chatHistoryRepository.save("chat", chatId);
        if (files == null || files.isEmpty()) {
            return textChat2(prompt, chatId);
        } else {
            return multiModalChat2(prompt, chatId, files);
        }
    }

    //深度思考带文件聊天
    private Flux<String> multiModalChat2(String prompt, String chatId, List<MultipartFile> files) {
        Media[] medias = files.stream().map(file ->
                new Media(MediaType.valueOf(Objects.requireNonNull(file.getContentType())), file.getResource())
        ).toArray(Media[]::new);

        Flux<ChatResponse> chatResponse = dashScopeChatClient.prompt()
                .user(c -> c.text(prompt).media(medias))
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

    //深度思考聊天
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

    public List<MessagesVO> getArray() {
        return array;
    }

    public void setArray(List<MessagesVO> array) {
        this.array = array;
    }
}
