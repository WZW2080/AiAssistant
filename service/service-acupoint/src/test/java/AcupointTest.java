
import com.acupoint.AcupointMainApplication;

import com.acupoint.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import res.Result;


/**
 * @Description: 测试数据库连接
 * @Author: WangZhiWei
 * @Time: 2025/3/24 11:17
 */
@SpringBootTest(classes = AcupointMainApplication.class)
public class AcupointTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void test() {
        Result result = weatherService.getWeatherInfo("110101","");
        System.out.println(result);
    }
}
