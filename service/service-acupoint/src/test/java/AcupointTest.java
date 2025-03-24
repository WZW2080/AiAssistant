import acupoint.TestAcupoint;
import com.acupoint.AcupointMainApplication;
import com.acupoint.mapper.AcupointMapper;
import com.acupoint.service.AcupointService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description: 测试数据库连接
 * @Author: WangZhiWei
 * @Time: 2025/3/24 11:17
 */
@SpringBootTest(classes = AcupointMainApplication.class)
public class AcupointTest {

    @Autowired
    private AcupointMapper acupointMapper;
    @Test
    void insert(){
        TestAcupoint testAcupoint = new TestAcupoint();
        testAcupoint.setAid(2);
        testAcupoint.setName("中部");
        testAcupoint.setPosition("111");
        testAcupoint.setDescription("111");
        int save = acupointMapper.insert(testAcupoint);
        System.out.println(save);
    }
}
