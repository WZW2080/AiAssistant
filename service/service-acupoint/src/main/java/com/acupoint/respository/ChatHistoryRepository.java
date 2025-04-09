package com.acupoint.respository;

import com.acupoint.entity.po.HistoryRepository;

import java.util.List;

public interface ChatHistoryRepository {

    /**
     * 保存会话记录id
     * @param type 业务类型：chat、service、pdf
     * @param chatId 会话id
     */
    void save(String type,String chatId);

    /**
     *获取会话id列表
     * @param type 业务类型
     * @return 会话ID列表
     */
    List<HistoryRepository> getChatIds(String type);

    /**
     * 更新会话记录标题
     * @param type 业务类型
     * @param historyRepository 会话记录实体
     */
    void updateTitle(String type, HistoryRepository historyRepository);

    /**
     * 删除会话记录 id
     * @param type 业务类型：chat、service、pdf
     * @param chatId 会话id
     */
    void delete(String type,String chatId);
}
