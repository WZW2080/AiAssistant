package com.acupoint.respository.impl;

import com.acupoint.entity.po.HistoryRepository;
import com.acupoint.respository.ChatHistoryRepository;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InRedisChatHistoryRepository implements ChatHistoryRepository {
    public static final String CHAT_SESSION_PREFIX = "chat_session:";
    private static final String REDIS_KEY_PREFIX = "chatmemory:";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private InRedisReasoningHistoryRepository inRedisReasoningHistoryRepository;

    /**
     * 保存会话id
     *
     * @param type   业务类型：chat、service、pdf
     * @param chatId 会话id
     */
    @Override
    public void save(String type, String chatId) {
        String key = CHAT_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        //判断是否有相同id
        for (String string : strings) {
            HistoryRepository repository = JSON.parseObject(string, HistoryRepository.class);
            //再判断是否存在相同id
            if (repository.getChatId().equals(chatId)) {
                //存在则不添加
                return;
            }
        }
        //没有相同id的就直接添加
        HistoryRepository repository = new HistoryRepository(chatId, chatId);
        stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(repository));
    }

    /**
     * 获取所有会话id
     *
     * @param type 业务类型
     * @return
     */
    @Override
    public List<HistoryRepository> getChatIds(String type) {
        String key = CHAT_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (strings != null) {
            //将JSON字符串转换为对象数组并返回
            return strings.stream().map(s -> JSON.parseObject(s, HistoryRepository.class)).toList();
        }
        return List.of();
    }

    /**
     * 更新会话标题
     *
     * @param type              业务类型
     * @param historyRepository 会话记录实体
     */
    @Override
    public void updateTitle(String type, HistoryRepository historyRepository) {
        String key = CHAT_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        List<HistoryRepository> historyRepositoryList = strings.stream().map(s -> JSON.parseObject(s, HistoryRepository.class)).toList();
        //遍历集合，找到要更新的记录，并更新
        for (HistoryRepository repository : historyRepositoryList) {
            if (repository.getChatId().equals(historyRepository.getChatId())) {
                //根据key找到原有记录并删除
                stringRedisTemplate.opsForList().remove(key, 1, JSON.toJSONString(repository));
                //更新标题
                repository.setTitle(historyRepository.getTitle());
                //把新的记录存储进去
                stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(repository));
                return;
            }
        }
    }

    /**
     * 删除会话记录id
     * @param type 业务类型：chat、service、pdf
     * @param chatId 会话id
     */
    @Override
    public void delete(String type, String chatId) {
        String key = CHAT_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        for (String string : strings) {
            HistoryRepository repository = JSON.parseObject(string, HistoryRepository.class);
            //再判断是否存在相同id
            if (repository.getChatId().equals(chatId)) {
                //存在则删除会话记录id
                stringRedisTemplate.opsForList().remove(key, 1, JSON.toJSONString(repository));
                //还要删除聊天记录
                stringRedisTemplate.delete(REDIS_KEY_PREFIX + repository.getChatId());
                //还有删除思考记录
                inRedisReasoningHistoryRepository.delete(type, repository.getChatId());
                return;
            }
        }
    }
}
