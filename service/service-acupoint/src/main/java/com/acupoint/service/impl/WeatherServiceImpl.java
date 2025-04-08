package com.acupoint.service.impl;

import com.acupoint.feign.WeatherFeignClient;
import com.acupoint.service.WeatherService;
import gdAPI.Geo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import res.Result;
import gdAPI.WeatherInfo;

import java.util.List;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/8 14:27
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    private final String key = "b96a421892ade8354932b701dd317e2d";
    @Autowired
    private WeatherFeignClient weatherFeignClient;
    @Override
    public Result getWeatherInfo(String city, String extensions) {
        WeatherInfo weatherInfo = null;
        try {
            Geo geo = weatherFeignClient.geoInfo(key, city);
            List<Geo.Geocode> geocodes = geo.getGeocodes();
            weatherInfo = weatherFeignClient.getWeatherInfo(key,geocodes.getFirst().getAdcode(), extensions);
        } catch (Exception e) {
            return Result.error(400,"无法获取天气，可能城市填写错误或者其它原因");
        }
        return Result.ok(weatherInfo);
    }

    @Override
    public Result getGeoInfo(String address) {
        Geo geo = weatherFeignClient.geoInfo(key, address);
        return Result.ok(geo);
    }
}
