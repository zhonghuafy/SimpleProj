package org.fe.ek.test.proj.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: SimpleProj
 * @description: redis example, also could be used as a tool. but not necessary cause spring boot had provided very convenient utils
 * @author: Wang Zhenhua
 * @create: 2020-03-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-06
 **/
@Service
public class RedisRW {

    private static final String REDIS_PREFIX = "COM_YUNDA_SYS_USER_PORTRAIT_PREFIX_";

    private static final long expireTime = 0;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setExpire(@NotNull String key, long expire) {
        stringRedisTemplate.expire(REDIS_PREFIX + key, expire, TimeUnit.MILLISECONDS);
    }

    public void setExpire(@NotNull String key, long expire, @NotNull TimeUnit unit) {
        stringRedisTemplate.expire(REDIS_PREFIX + key, expire, unit);
    }

    public void delete(@NotNull String key) {
        stringRedisTemplate.delete(REDIS_PREFIX + key);
    }

    public void setString(@NotNull String key, String value) {
        stringRedisTemplate.opsForValue().set(REDIS_PREFIX + key, value, expireTime);
    }

    public String getString(@NotNull String key) {
        return stringRedisTemplate.opsForValue().get(REDIS_PREFIX + key);
    }

    public List<String> getString(@NotEmpty List<String> keyList) {
        List<String> list = keyList.stream().map(a -> REDIS_PREFIX + a).collect(Collectors.toList());
        return stringRedisTemplate.opsForValue().multiGet(list);
    }

    public void setHash(@NotNull String key, @NotNull Map<String, String> value) {
        stringRedisTemplate.boundHashOps(REDIS_PREFIX + key).putAll(value);
    }

    public void setHash(@NotNull String key, @NotNull String hash, String value) {
        stringRedisTemplate.boundHashOps(REDIS_PREFIX + key).put(hash, value);
    }

    public Object getHash(@NotNull String key, @NotNull String hash) {
        return stringRedisTemplate.boundHashOps(REDIS_PREFIX + key).get(hash);
    }

    public List<Object> getHash(@NotEmpty List<String> keyList) {
        return stringRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            keyList.forEach(a -> {
                connection.hGetAll(stringRedisTemplate.getStringSerializer().serialize(a));
            });
            return null;
        });
    }

    public void delHash(@NotNull String key, @NotNull String hash) {
        stringRedisTemplate.boundHashOps(REDIS_PREFIX + key).delete(hash);
    }

    public void rightPushList(@NotNull String key, String value) {
        stringRedisTemplate.boundListOps(REDIS_PREFIX + key).rightPush(value);
    }

    public String getListItem(@NotNull String key, long index) {
        return stringRedisTemplate.boundListOps(REDIS_PREFIX + key).index(index);
    }

    public String popListLeft(@NotNull String key) {
        return stringRedisTemplate.boundListOps(REDIS_PREFIX + key).leftPop();
    }
}
