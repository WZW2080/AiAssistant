package com.acupoint.controller;

import acupoint.TestAcupoint;
import com.acupoint.service.AcupointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("acupointPage")
    @Operation(summary = "分页查询")
    public Result getAcupointPage(@RequestParam(defaultValue = "1") Integer current,
                                  @RequestParam(defaultValue = "10") Integer size) {
        return Result.ok(acupointService.getAcupointPage(current, size));
    }
    @PostMapping("insertAcupoint")
    @Operation(summary = "新增")
    public Result insert(TestAcupoint acupoint) {
        return Result.ok(acupointService.save(acupoint));
    }

    public Result test(){
        System.out.println("11111111111111111");
        return Result.ok();
    }

    @GetMapping("test2")
    public Result test2(){
        return Result.ok();
    }

}
