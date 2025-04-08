package gdAPI;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Description: 天气信息实体类
 * @Author: WangZhiWei
 * @Time: 2025/4/8 14:33
 */
@Data
public class WeatherInfo {
    private  String status; //值为0或1。1：成功；0：失败
    private String count; //返回结果总数目
    private String info; //返回的状态信息
    private String infocode; // 返回状态说明,10000代表正确
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Forecast> forecasts;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Live> lives;

}
