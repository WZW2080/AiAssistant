package gdAPI;

import lombok.Data;

import java.util.List;

/**
 * @Description: 预报天气信息数据
 * @Author: WangZhiWei
 * @Time: 2025/4/8 14:39
 */
@Data
public class Forecast {
    private String province;    //省份名
    private String city;//城市名
    private String adcode;// 区域编码
    private String reporttime;//发布时间
    private List<Cast> casts;
}
