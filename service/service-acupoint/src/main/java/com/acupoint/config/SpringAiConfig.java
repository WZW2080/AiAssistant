package com.acupoint.config;

import com.acupoint.chatMemony.RedisChatMemory;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.acupoint.constants.SystemConstant.*;

@Configuration
public class SpringAiConfig {

    @Bean
    public ChatMemory chatMemory() {
        return new RedisChatMemory();
    }

    @Bean
    public ChatMemory inMemoryChatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient
                .builder(model)
                .defaultSystem(CHAT_SYSTEM_PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor(), // 添加一个简单的日志记录器
                        new MessageChatMemoryAdvisor(chatMemory)// 添加一个消息记忆的记录器
                )
                .build();
    }

    @Bean
    public ChatClient dashScopeChatClient(DashScopeChatModel dashScopeChatModel, ChatMemory chatMemory) {
        return ChatClient
                .builder(dashScopeChatModel)
                .defaultSystem(CHAT_SYSTEM_PROMPT_2)
                .defaultAdvisors(new SimpleLoggerAdvisor(), // 添加一个简单的日志记录器
                        new MessageChatMemoryAdvisor(chatMemory) // 添加一个消息记忆的记录器
                )
                .build();
    }
}
