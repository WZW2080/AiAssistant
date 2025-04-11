package com.acupoint.respository;

import com.acupoint.entity.po.HistoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryChatHistoryRepository implements ChatHistoryRepository {

    // 模拟一个简单的内存数据库
    private final Map<String, List<HistoryRepository>> chatHistory = new HashMap<>();

    /**
     * 根据业务类型保存会话id
     *
     * @param type   业务类型：chat
     * @param chatId 会话id
     */
    @Override
    public void save(String type, String chatId) {
        //如果不存在该类型的会话id，则创建一个空的列表
        if (!chatHistory.containsKey(type)) {
            chatHistory.put(type, new ArrayList<>());
        }

        //添加会话id
        List<HistoryRepository> historyRepositories = chatHistory.get(type);

        for (HistoryRepository historyRepository : historyRepositories) {
            //再判断是否存在相同id
            if (historyRepository.getChatId().equals(chatId)) {
                return;
            }
        }
        //没有相同id的就直接添加
        HistoryRepository historyRepository = new HistoryRepository(chatId, chatId); //默认标题为会话id
        historyRepositories.add(historyRepository);
    }

    /**
     * 获取该类型所有会话id
     *
     * @param type 业务类型
     * @return
     */
    @Override
    public List<HistoryRepository> getChatIds(String type) {
        List<HistoryRepository> historyRepositories = chatHistory.get(type);
        return historyRepositories != null ? historyRepositories : List.of();
    }

    /**
     * 更新会话记录的标题
     *
     * @param type              业务类型
     * @param historyRepository 会话记录实体
     */
    @Override
    public void updateTitle(String type, HistoryRepository historyRepository) {
        //先获取该类型的所有会话记录
        List<HistoryRepository> historyRepositories = chatHistory.get(type);
        //提取chatId和title
        String chatId = historyRepository.getChatId();
        String title = historyRepository.getTitle();
        //遍历会话记录
        for (HistoryRepository repository : historyRepositories) {
            if (repository.getChatId().equals(chatId)) {
                //如果相同则修改title
                repository.setTitle(title);
                break;
            }
        }
    }

    @Override
    public void delete(String type, String chatId) {
        //如果不存在该类型的会话id，直接返回
        if (!chatHistory.containsKey(type)) {
            return;
        }
        List<HistoryRepository> historyRepositories = chatHistory.get(type);
        //遍历列表，找到相同id的元素并删除
        for (HistoryRepository historyRepository : historyRepositories) {
            if (historyRepository.getChatId().equals(chatId)) {
                historyRepositories.remove(historyRepository);
                break;
            }
        }

    }
}
