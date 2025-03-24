package res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回的结果集
 * @Author: WangZhiWei
 * @Time: 2025/3/24 11:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result ok() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result ok(Object data) {
        Result result = ok();
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("error");
        return result;
    }

    public static Result error(Integer code) {
        Result error = error();
        error.setCode(code);
        return error;
    }

    public static Result error(Integer code, String msg) {
        Result error = error();
        error.setCode(code);
        error.setMsg(msg);
        return error;
    }
}
