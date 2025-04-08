package gdAPI;

import lombok.Data;

/**
 * @Description: 实况天气数据信息
 * @Author: WangZhiWei
 * @Time: 2025/4/8 14:36
 */
@Data
public class Live {
    private String province;    //省份名
    private String city;//城市名
    private String adcode;// 区域编码
    private String weather;//天气现象（汉字描述）
    private String temperature;//实时气温，单位：摄氏度
    private String winddirection;//风向描述
    private String windpower;//风力级别，单位：级
    private String humidity;//空气湿度
    private String reporttime;//发布时间
}
