package com.acupoint.feign;

import gdAPI.Geo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import gdAPI.WeatherInfo;

/**
 * @description: 远程调用高德地图天气api接口
 * @author: WangZhiWei
 * @time: 2025/3/20 10:41
 */
//v3/weather/weatherInfo?
@FeignClient(name = "services-weather",url = "https://restapi.amap.com/")
public interface WeatherFeignClient {


    /**
     * 获取天气信息
     * @param key api key
     * @param city 城市编码
     * @param extensions 气象类型  可选值：base/all
     */
    @GetMapping("v3/weather/weatherInfo")
    WeatherInfo getWeatherInfo(@RequestParam(value = "key",defaultValue = "") String key,
                               @RequestParam(value = "city",defaultValue = "") String city,
                               @RequestParam(value = "extensions",defaultValue = "base") String extensions);

    @GetMapping("/v3/geocode/geo")
    Geo geoInfo(@RequestParam(value = "key",defaultValue = "") String key,
                @RequestParam(value = "address",defaultValue = "") String address);
}
