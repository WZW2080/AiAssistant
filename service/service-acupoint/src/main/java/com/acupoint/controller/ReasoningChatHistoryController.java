package com.acupoint.controller;

import com.acupoint.entity.vo.ReasoningContentMessageVO;
import com.acupoint.respository.ReasoningHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai/reasoningMessage")
public class ReasoningChatHistoryController {

    @Autowired
    private ReasoningHistoryRepository reasoningHistoryRepository;

    /**
     * 保存 reasoningContentMessageVO
     *
     * @param type
     * @param reasoningContentMessageVO
     */
    @PostMapping("/saveReasoningMessage/{type}")
    public void saveReasoningMessage(@PathVariable("type") String type, @RequestBody ReasoningContentMessageVO reasoningContentMessageVO) {
        reasoningHistoryRepository.save(type, reasoningContentMessageVO);
    }

    /**
     * 获取 所有reasoningContentMessageVO
     * @param type
     * @return
     */
    @GetMapping("/getReasoningMessageAll/{type}")
    public List<ReasoningContentMessageVO> getAll(@PathVariable String type) {
        return reasoningHistoryRepository.getAll(type);
    }
}
