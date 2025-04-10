package acupoint;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 测试穴位实体类
 * @Author: WangZhiWei
 * @Time: 2025/3/24 10:51
 */
@Data
@TableName(value ="acupoint")
public class TestAcupoint {
    @TableId
    private Integer aid; //主键
    private String name; //穴位名称
    private String position; //穴位位置
    private String description; //穴位描述
    private String treatment; //穴位主治
    private String stitch; //穴位针法
    private Integer meridians; //所属经络
}
