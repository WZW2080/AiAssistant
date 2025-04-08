package com.acupoint.controller;

import acupoint.TestAcupoint;
import com.acupoint.mapper.AcupointMapper;
import com.acupoint.service.AcupointService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@RequestMapping(value = "acupoint")
@CrossOrigin
public class AcupointController {
    @Autowired
    private AcupointService acupointService;
    @Autowired
    private AcupointMapper AcupointMapper;
    @GetMapping("acupointList")
    @Operation(summary = "查询所有穴位")
    public Result getAcupointList() {
        return Result.ok(acupointService.list());
    }
    @GetMapping("acupointByName")
    @Operation(summary = "根据穴位名称查询")
    public Result getAcupointByName(@RequestParam String name) {
        return Result.ok(acupointService.getOne(new QueryWrapper<TestAcupoint>().eq("name",name)));
    }
    @GetMapping("acupointPage")
    @Operation(summary = "分页查询")
    public Result getAcupointPage(@RequestParam(defaultValue = "1") Integer current,
                                  @RequestParam(defaultValue = "10") Integer size) {
        return Result.ok(acupointService.getAcupointPage(current, size));
    }
    @GetMapping("acupointByMeridians")
    @Operation(summary = "根据经脉的id查询该经脉上所有穴位")
    public Result acupointByMeridians(@RequestParam(defaultValue = "") Integer id,@RequestParam(defaultValue = "") String name) {
        return Result.ok(AcupointMapper.acupointByMeridians(id,name));
    }
    @GetMapping("test2")
    public Result test2(){
        return Result.ok();
    }

}
