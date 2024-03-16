package com.safe.safecampusbackend.util.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

public class RedisUtil {
    private static final JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
    private static final Logger logger = LogManager.getLogger(RedisUtil.class);

    /**
     * 设置 Redis 缓存
     *
     * @param key             键
     * @param secondsToExpire 过期时间（单位：秒
     * @param value           值
     */
    public static void setCache(String key, int secondsToExpire, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, secondsToExpire, value);
        } catch (JedisConnectionException e) {
            // 处理连接异常
            logger.error(e);
        } catch (JedisException e) {
            // 处理 Redis 异常
            logger.error(e);
        }
    }

    /**
     * 取得 Redis 缓存
     *
     * @param key 键
     * @return 键值对
     */
    public static String getCache(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (JedisConnectionException e) {
            // 处理连接异常
            logger.error(e);
            return null;
        } catch (JedisException e) {
            // 处理 Redis 异常
            logger.error(e);
            return null;
        }
    }

    /**
     * 移除 Redis 缓存
     *
     * @param key 键
     */
    public static void deleteCache(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (JedisConnectionException e) {
            // 处理连接异常
            logger.error(e);
        } catch (JedisException e) {
            // 处理 Redis 异常
            logger.error(e);
        }
    }
}
