package com.three.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

/**
 * redis管理类
 */
public class RedisManager {

  private RedisEngine redisEngine;

  public boolean set(String key, String value, int expireSeconds) {
    Jedis jedis = getJedis();
    try {
      String ret = jedis.set(key, value);
      if (expireSeconds > 0) {
        jedis.expire(key, expireSeconds);
      }
      return "OK".equals(ret);
    } finally {
      jedis.close();
    }
  }

  public String get(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.get(key);
    } finally {
      jedis.close();
    }
  }

  public boolean exists(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.exists(key);
    } finally {
      jedis.close();
    }
  }

  public Long incr(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.incr(key);
    } finally {
      jedis.close();
    }
  }

  public Long incrBy(String key, long delta) {
    Jedis jedis = getJedis();
    try {
      return jedis.incrBy(key, delta);
    } finally {
      jedis.close();
    }
  }

  // return: the number of keys that were remove
  public Long del(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.del(key);
    } finally {
      jedis.close();
    }
  }

  public Long expire(String key, int expireSeconds) {
    Jedis jedis = getJedis();
    try {
      return jedis.expire(key, expireSeconds);
    } finally {
      jedis.close();
    }
  }

  /* 当key不存在时就原子设置成value，可以基于本方法做业务抢锁 */
  public boolean setNxEx(String key, String value, int expireSeconds) {
    Jedis jedis = getJedis();
    try {
      String reply = jedis.set(key, value, "NX", "EX", expireSeconds);
      return "OK".equals(reply);
    } finally {
      jedis.close();
    }
  }

  public Long setNx(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.setnx(key, value);
    } finally {
      jedis.close();
    }
  }

  // return: the number of elements added to the sorted sets, not including elements already
  // existing for which the score was updated
  public Long zadd(String key, double score, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.zadd(key, score, value);
    } finally {
      jedis.close();
    }
  }

  // return: the size of sortedset
  public Long zcard(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.zcard(key);
    } finally {
      jedis.close();
    }
  }

  // return 1 if the element was removed 0 if the new element was not a member of the set
  public Long zrem(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.zrem(key, value);
    } finally {
      jedis.close();
    }
  }

  public Double zscore(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.zscore(key, value);
    } finally {
      jedis.close();
    }
  }

  // return: the new score of member (a double precision floating point number), represented as
  // string
  public double zincrby(String key, double score, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.zincrby(key, score, value);
    } finally {
      jedis.close();
    }
  }

  public Set<String> zrange(String key, long start, long end) {
    Jedis jedis = getJedis();
    try {
      return jedis.zrange(key, start, end);
    } finally {
      jedis.close();
    }
  }

  public Set<Tuple> zrangeWithScores(String key, long start, long end) {
    Jedis jedis = getJedis();
    try {
      return jedis.zrangeWithScores(key, start, end);
    } finally {
      jedis.close();
    }
  }

  public Set<String> zrevrange(String key, long start, long end) {
    Jedis jedis = getJedis();
    try {
      return jedis.zrevrange(key, start, end);
    } finally {
      jedis.close();
    }
  }

  public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
    Jedis jedis = getJedis();
    try {
      return jedis.zrevrangeWithScores(key, start, end);
    } finally {
      jedis.close();
    }
  }

  public Long hlen(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.hlen(key);
    } finally {
      jedis.close();
    }
  }

  public Long hset(String key, String field, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.hset(key, field, value);
    } finally {
      jedis.close();
    }
  }

  public String hmset(String key, Map<String, String> value) {
    Jedis jedis = getJedis();
    try {
      return jedis.hmset(key, value);
    } finally {
      jedis.close();
    }
  }

  public String hget(String key, String field) {
    Jedis jedis = getJedis();
    try {
      return jedis.hget(key, field);
    } finally {
      jedis.close();
    }
  }

  public Map<String, String> hgetAll(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.hgetAll(key);
    } finally {
      jedis.close();
    }
  }

  public Long hdel(String key, String field) {
    Jedis jedis = getJedis();
    try {
      return jedis.hdel(key, field);
    } finally {
      jedis.close();
    }
  }

  public Long hincrby(String key, String field, long value) {
    Jedis jedis = getJedis();
    try {
      return jedis.hincrBy(key, field, value);
    } finally {
      jedis.close();
    }
  }

  public Long hsetnx(String key, String field, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.hsetnx(key, field, value);
    } finally {
      jedis.close();
    }
  }

  // return: the number of elements that were added to the set, not including all the elements
  // already present into the set
  public Long sadd(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.sadd(key, value);
    } finally {
      jedis.close();
    }
  }

  // return: the size of set
  public Long scard(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.scard(key);
    } finally {
      jedis.close();
    }
  }

  // return if member is a member of the set stored at key.
  public boolean sismember(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.sismember(key, value);
    } finally {
      jedis.close();
    }
  }

  public Set<String> smembers(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.smembers(key);
    } finally {
      jedis.close();
    }
  }

  // return 1 if the new element was removed 0 if the new element was not a member of the set
  public Long srem(String key, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.srem(key, value);
    } finally {
      jedis.close();
    }
  }

  // return the number of elements inside the list after the push operation
  public Long rpush(String key, String... value) {
    Jedis jedis = getJedis();
    try {
      return jedis.rpush(key, value);
    } finally {
      jedis.close();
    }
  }

  public List<String> lrange(String key, long start, long end) {
    Jedis jedis = getJedis();
    try {
      return jedis.lrange(key, start, end);
    } finally {
      jedis.close();
    }
  }

  public Long llen(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.llen(key);
    } finally {
      jedis.close();
    }
  }

  // return: the length of the list after the push operations
  public Long lpush(String key, String... value) {
    Jedis jedis = getJedis();
    try {
      return jedis.lpush(key, value);
    } finally {
      jedis.close();
    }
  }

  public Long lrem(String key, long count, String value) {
    Jedis jedis = getJedis();
    try {
      return jedis.lrem(key, count, value);
    } finally {
      jedis.close();
    }
  }

  public String lpop(String key) {
    Jedis jedis = getJedis();
    try {
      return jedis.lpop(key);
    } finally {
      jedis.close();
    }
  }

  // return the number of clients that received the message
  public Long publish(String key, String message) {
    Jedis jedis = getJedis();
    try {
      return jedis.publish(key, message);
    } finally {
      jedis.close();
    }
  }

  public void subscribe(JedisPubSub jedisPubSub, String channel) {
    Jedis jedis = getJedis();
    try {
      jedis.subscribe(jedisPubSub, channel);
    } finally {
      jedis.close();
    }
  }

  public void psubscribe(JedisPubSub jedisPubSub, String pattern) {
    Jedis jedis = getJedis();
    try {
      jedis.psubscribe(jedisPubSub, pattern);
    } finally {
      jedis.close();
    }
  }

  public Jedis getJedis() {
    return redisEngine.getJedis();
  }

  public RedisEngine getRedisEngine() {
    return redisEngine;
  }

  public void setRedisEngine(RedisEngine redisEngine) {
    this.redisEngine = redisEngine;
  }
}
