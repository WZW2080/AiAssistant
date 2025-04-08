package com.acupoint.controller;

import com.acupoint.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import res.Result;

/**
 * @Description: 天气查询控制层
 * @Author: WangZhiWei
 * @Time: 2025/4/8 15:13
 */
@RestController
@CrossOrigin
@RequestMapping("weather")
@Tag(name = "获取位置天气接口api")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("getInfo")
    @Operation(summary = "查询天气信息",
            description = "输入城市进行查询，extensions气象类型，可选值：base/all。" +
                    "\n\nbase:返回实况天气\n" +
                    "\n\nall:返回预报天气")
    public Result getInfo(@RequestParam String city,@RequestParam(defaultValue = "base") String extensions){
        return Result.ok(weatherService.getWeatherInfo(city,extensions));
    }

    @GetMapping("getGeo")
    @Operation(summary = "查询地理位置信息",
            description = "规则遵循：国家、省份、城市、区县、城镇、乡村、街道、门牌号码、屋邨、大厦。可组合，如：北京市朝阳区阜通东大街6号。")
    public Result getGeo(@RequestParam String address){
        return Result.ok(weatherService.getGeoInfo(address));
    }
}
