import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.redis.RedisApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: SimpleProj
 * @description: RedisTest
 * @author: Wang Zhenhua
 * @create: 2020-03-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-06
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisTest {

    private static final String REDIS_PREFIX = "COM_YUNDA_SYS_USER_PORTRAIT_PREFIX_";

    private static final long expireTime = 0;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        String rekey = "telephone";
        String key = "17602896602";
        String value = "{\"mailNo\": \"3927272070523\",\"receiveMobile\": \"18852908905\"}";
        String key2 = "18802329035";
        String value2 = "4303756343134";
        stringRedisTemplate.opsForValue().set(key, value, 30, TimeUnit.DAYS);
        log.info("redis key {} value {}", key, stringRedisTemplate.opsForValue().get(key));
//        delete(rekey);

//        setString(key, value);
//        log.info("data: {}", getString(key));

//        setHash(rekey, key2, value2);
//        log.info("data: {}", getHash(rekey, key2));

//        delHash(rekey, key2);
//        log.info("data: {}", getHash(rekey, key));
//        log.info("data: {}", getHash(rekey, key2));

//        stringRedisTemplate.boundListOps(REDIS_PREFIX + rekey).rightPush(rekey);
//        log.info("list: {}", stringRedisTemplate.boundListOps(REDIS_PREFIX + rekey).index(0));
//        log.info("list: {}", stringRedisTemplate.boundListOps(REDIS_PREFIX + rekey).leftPop());
//        log.info("list size: {}", stringRedisTemplate.boundListOps(REDIS_PREFIX + rekey).size());
    }

    /**
     * use multiGet() to get multiple keys
     */
    @Test
    public void testpipe() {
        stringRedisTemplate.opsForValue().set(REDIS_PREFIX + "17602896602","17602896602---1");
        stringRedisTemplate.opsForValue().set(REDIS_PREFIX + "18802329035","18802329035---223");
        List<String> keyList = Arrays.asList(REDIS_PREFIX + "17602896602",REDIS_PREFIX + "18802329035");

        List<String> valueList = stringRedisTemplate.opsForValue().multiGet(keyList);
        if (valueList != null) {
            valueList.forEach(log::info);
        }
        stringRedisTemplate.delete(keyList);
        log.info("key {} exist: {}", REDIS_PREFIX + "17602896602", stringRedisTemplate.hasKey(REDIS_PREFIX + "17602896602"));
    }

    /**
     * use pipeline to get string
     */
    @Test
    public void testpipe2() {
        stringRedisTemplate.opsForValue().set(REDIS_PREFIX + "17602896602","17602896602---1");
        stringRedisTemplate.opsForValue().set(REDIS_PREFIX + "18802329035","18802329035---223");
        List<String> keyList = Arrays.asList(REDIS_PREFIX + "17602896602",REDIS_PREFIX + "18802329035");

        List<Object> result = stringRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            keyList.forEach(((StringRedisConnection) connection)::get);
            return null;
        });
        result.forEach(a -> log.info("data: {}", a));
        stringRedisTemplate.delete(keyList);
        log.info("key {} exist: {}", REDIS_PREFIX + "17602896602", stringRedisTemplate.hasKey(REDIS_PREFIX + "17602896602"));
    }

    /**
     * use pipeline to get hash
     */
    @Test
    public void testpipe3() {
        Map<String, String> datamap = new HashMap<>();
        datamap.put("key1","value1");
        datamap.put("key2","value2");
        stringRedisTemplate.boundHashOps(REDIS_PREFIX + "17602896602").putAll(datamap);
        stringRedisTemplate.boundHashOps(REDIS_PREFIX + "18802329035").putAll(datamap);
        List<String> keyList = Arrays.asList(REDIS_PREFIX + "17602896602",REDIS_PREFIX + "18802329035");

        List<Object> result = stringRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            keyList.forEach(a -> {
                connection.hGetAll(stringRedisTemplate.getStringSerializer().serialize(a));
            });
            return null;
        });
        result.forEach(a -> log.info("data: {}", a));

        List<Object> resultKey1 = stringRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            keyList.forEach(a -> {
                connection.hGet(stringRedisTemplate.getStringSerializer().serialize(a), stringRedisTemplate.getStringSerializer().serialize("key1"));
            });
            return null;
        });
        resultKey1.forEach(a -> log.info("data key1: {}", a));
        stringRedisTemplate.delete(keyList);
        log.info("key {} exist: {}", REDIS_PREFIX + "17602896602", stringRedisTemplate.hasKey(REDIS_PREFIX + "17602896602"));
    }
}
