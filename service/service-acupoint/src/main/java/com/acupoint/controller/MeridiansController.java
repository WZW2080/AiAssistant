package com.acupoint.controller;

import com.acupoint.service.MeridiansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import res.Result;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/7 15:30
 */
@CrossOrigin
@RestController
@Tag(name = "经脉接口api", description = "当前版仅测试")
@RequestMapping("meridians")
public class MeridiansController {

    @Autowired
    private MeridiansService meridiansService;
    @GetMapping("list")
    @Operation(summary = "查询所有的经脉")
    public Result list(){
        return Result.ok(meridiansService.list());
    }
}
