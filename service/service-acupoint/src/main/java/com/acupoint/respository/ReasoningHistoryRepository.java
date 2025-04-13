package com.acupoint.respository;

import com.acupoint.entity.po.ReasoningContentMessage;
import com.acupoint.entity.vo.ReasoningContentMessageVO;

import java.util.List;

public interface ReasoningHistoryRepository {
    //保存会话信息含推理内容
    void save(String type,ReasoningContentMessageVO reasoningContentMessageVO);

    //获取所有会话信息含推理内容
    List<ReasoningContentMessageVO> getAll(String type);

    //根据会话类型currentChatId删除会话信息
    void delete(String type,String currentChatId);
}
