import com.my.study.StudyRedis;
import com.my.study.lock.RedisQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudyRedis.class})
public class RedisTest {

    @Autowired
    private RedisQueue redisQueue;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            redisQueue.push("test" + i);
        }
        while (true) {
            System.out.println(redisQueue.pop());
        }
    }
}
