package com.seal.util.util;

import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description Redis 配置类
 **/
public class TplhkStringRedisSerializer extends StringRedisSerializer {
    private final Charset charset;

    private String keyPrefix;

    public TplhkStringRedisSerializer(String keyPrefix) {
        this(StandardCharsets.UTF_8);
        this.keyPrefix = keyPrefix;
    }

    public TplhkStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
     */
    @Override
    public String deserialize(@Nullable byte[] bytes) {
        String saveKey = new String(bytes, charset);
        if (saveKey.startsWith(keyPrefix + ":")) {
            saveKey = saveKey.substring((keyPrefix + ":").length(), saveKey.length());
        }
        return (saveKey.getBytes() == null ? null : saveKey);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
     */
    @Override
    public byte[] serialize(@Nullable String string) {
        String key = keyPrefix + ":" + string;
        return (key == null ? null : key.getBytes(charset));
    }
}
