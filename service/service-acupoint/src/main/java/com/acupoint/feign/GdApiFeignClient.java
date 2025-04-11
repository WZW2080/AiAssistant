package com.acupoint.feign;

import gdAPI.Geo;
import gdAPI.GeoTip;
import org.springframework.beans.factory.annotation.Value;
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
@FeignClient(name = "services-weather", url = "https://restapi.amap.com/")
public interface GdApiFeignClient {


    /**
     * 获取天气信息
     *
     * @param key        api key
     * @param city       城市编码
     * @param extensions 气象类型  可选值：base/all
     */
    @GetMapping("v3/weather/weatherInfo")
    WeatherInfo getWeatherInfo(@RequestParam String key,
                               @RequestParam String city,
                               @RequestParam(value = "extensions", defaultValue = "base") String extensions);

    @GetMapping("/v3/geocode/geo")
    Geo geoInfo(@RequestParam String key,
                @RequestParam(value = "address", defaultValue = "") String address);

    /**
     * 根据用户输入的关键词查询返回建议列表
     * @param key api key
     * @param keywords 关键词
     * @param city 搜索城市，填入此参数后，会尽量优先返回此城市数据，但是不一定仅局限此城市结果
     */
    @GetMapping("/v3/assistant/inputtips")
    GeoTip geoTip(@RequestParam String key,
                  @RequestParam(value = "keywords", defaultValue = "") String keywords,
                  @RequestParam(value = "city", defaultValue = "") String city);
}
