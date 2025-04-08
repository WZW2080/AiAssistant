package meridians;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/7 15:26
 */
@TableName(value ="meridians")
@Data
public class Meridians {
    @TableId
    private Integer mid;
    private String name;
}
