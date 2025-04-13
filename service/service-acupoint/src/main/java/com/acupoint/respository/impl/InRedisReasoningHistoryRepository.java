package com.acupoint.respository.impl;

import com.acupoint.entity.po.HistoryRepository;
import com.acupoint.entity.po.ReasoningContentMessage;
import com.acupoint.entity.vo.ReasoningContentMessageVO;
import com.acupoint.respository.ReasoningHistoryRepository;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InRedisReasoningHistoryRepository implements ReasoningHistoryRepository {
    public static final String CHAT_Reasoning_SESSION_PREFIX = "chat_reasoning_session:";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param type                      业务类型
     * @param reasoningContentMessageVO 会话信息对象含推理内容
     */
    @Override
    public void save(String type, ReasoningContentMessageVO reasoningContentMessageVO) {
        String key = CHAT_Reasoning_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (!strings.isEmpty()) {
            //判断是否有相同会话id
            for (String string : strings) {
                ReasoningContentMessageVO rcm = JSON.parseObject(string, ReasoningContentMessageVO.class);
                //再判断是否存在相同id
                if (rcm.getCurrentChatId().equals(reasoningContentMessageVO.getCurrentChatId())) {
                    // 删除原有的对象
                    stringRedisTemplate.opsForList().remove(key, 0, JSON.toJSONString(rcm));
                    // 插入新的对象
                    stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(reasoningContentMessageVO));
                    return;
                }
            }
            //如果没有就直接插入数据
            stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(reasoningContentMessageVO));
        } else {
            //如果为空就直接插入数据
            stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(reasoningContentMessageVO));
        }
    }

    /**
     * 获取所有会话信息含AI推理内容
     *
     * @return 所有会话信息含AI推理内容
     */
    @Override
    public List<ReasoningContentMessageVO> getAll(String type) {
        String key = CHAT_Reasoning_SESSION_PREFIX + type;
        List<String> range = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (range != null) {
            return range.stream().map(s -> JSON.parseObject(s, ReasoningContentMessageVO.class)).toList();
        }
        return List.of();
    }

    @Override
    public void delete(String type, String currentChatId) {
        String key = CHAT_Reasoning_SESSION_PREFIX + type;
        List<String> strings = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (strings == null) {
            return;
        }
        for (String string : strings) {
            ReasoningContentMessageVO reasoningContentMessageVO = JSON.parseObject(string, ReasoningContentMessageVO.class);
            if (reasoningContentMessageVO.getCurrentChatId().equals(currentChatId)) {
                //相同则删除
                stringRedisTemplate.opsForList().remove(key, 1, JSON.toJSONString(reasoningContentMessageVO));
                return;
            }
        }
    }
}
