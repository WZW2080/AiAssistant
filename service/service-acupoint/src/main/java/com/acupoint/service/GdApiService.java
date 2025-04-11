package com.acupoint.service;

import res.Result;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/8 14:26
 */
public interface GdApiService {

    Result getWeatherInfo(
                          String city,
                          String extensions);

    Result getGeoInfo(String address);

    Result getGeoTips(String keywords,String city);
}
