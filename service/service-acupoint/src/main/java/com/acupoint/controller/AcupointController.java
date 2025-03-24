package com.acupoint.controller;

import acupoint.TestAcupoint;
import com.acupoint.service.AcupointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import res.Result;

/**
 * @Description: 穴位接口控制层
 * @Author: WangZhiWei
 * @Time: 2025/3/24 11:51
 */
@RestController
@Tag(name = "穴位接口api", description = "当前版仅测试")
@RequestMapping(value = "other")
public class AcupointController {
    @Autowired
    private AcupointService acupointService;
    @GetMapping("acupointList")
    @Operation(summary = "查询所有穴位")
    public Result getAcupointList() {
        return Result.ok(acupointService.list());
    }

    @PostMapping("insertAcupoint")
    @Operation(summary = "新增")
    public Result insert(TestAcupoint acupoint) {
        return Result.ok(acupointService.save(acupoint));
    }

    public Result test(){
        return Result.ok();
    }

}
