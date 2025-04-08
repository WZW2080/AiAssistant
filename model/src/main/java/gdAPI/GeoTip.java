package gdAPI;

import lombok.Data;

import java.util.List;

/**
 * @Description: 输入地理位置进行提示实体类
 * @Author: WangZhiWei
 * @Time: 2025/4/8 17:00
 */
@Data
public class GeoTip {
    private List<Tip> tips;//建议提示列表
    private String status;//返回状态
    private String info;//返回的状态信息
    private String count;//返回结果总数目

    @Data
    static class Tip{
        private Object id; // 返回数据 ID
        private String name;//tip 名称
        private String district;//所属区域 省+市+区（直辖市为“市+区”）
        private String adcode; //区域编码
        private Object location; //tip 中心点坐标
        private Object address; //详细地址
    }
}

