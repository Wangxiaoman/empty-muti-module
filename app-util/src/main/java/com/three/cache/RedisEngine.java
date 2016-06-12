package com.three.cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisEngine {
  public static Logger logger = Logger.getLogger(RedisEngine.class);
  
	private static JedisPool redisPool;

	private String redisIps = "localhost:6379";
	private Boolean redisTestOnBorrow = true;
	private Boolean redisTestOnReturn = true;
	private Integer redisMaxIdle = 50;
	private Integer redisMinIdle = 0;
	private Boolean redisTestWhileIdle = true;
	private Integer redisNumTestsPerEvictionRun = 10;
	private Integer redisTimeBetweenEvictionRunsMillis = 30000;
	private Integer redisTimeOut = 2000;

	// 初始化
	public void init() {
	  logger.info("===init RedisEngine ===");
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(redisTestOnBorrow);
		poolConfig.setTestOnReturn(redisTestOnReturn);
		poolConfig.setMaxIdle(redisMaxIdle);
		poolConfig.setMinIdle(redisMinIdle);
		poolConfig.setTestWhileIdle(redisTestWhileIdle);
		poolConfig.setNumTestsPerEvictionRun(redisNumTestsPerEvictionRun);
		poolConfig.setTimeBetweenEvictionRunsMillis(redisTimeBetweenEvictionRunsMillis);
		if (StringUtils.isNotEmpty(this.redisIps)) {
		  // 配置的格式为ip:端口:password
			Integer port = null;
			String[] ss = redisIps.trim().split(":");
			String ip = ss[0];
      if (ss.length > 1) {
        port = Integer.valueOf(ss[1]);
      } else {
        port = 6379;
      }
      if (ss.length > 3) {
        redisPool = new JedisPool(poolConfig, ip, port, redisTimeOut, ss[2] + ":" + ss[3]);
      } else if (ss.length == 3) {
        redisPool = new JedisPool(poolConfig, ip, port, redisTimeOut, ss[2]);
      } else {
        redisPool = new JedisPool(poolConfig, ip, port, redisTimeOut);
      }
		}
		
		logger.info("===init RedisEngine finish ===");
	}

	public void destroy() {

		if (redisPool != null) {
			redisPool.destroy();
		}
	}

	public Jedis getJedis() {
		return redisPool.getResource();
	}

	public static JedisPool getRedisPool() {
		return redisPool;
	}

	public static void setRedisPool(JedisPool redisPool) {
		RedisEngine.redisPool = redisPool;
	}

	public String getRedisIps() {
		
		return redisIps;
	}

	public void setRedisIps(String redisIps) {
		
		this.redisIps = redisIps;
	}

	public Boolean getRedisTestOnBorrow() {
		return redisTestOnBorrow;
	}

	public void setRedisTestOnBorrow(Boolean redisTestOnBorrow) {
		this.redisTestOnBorrow = redisTestOnBorrow;
	}

	public Boolean getRedisTestOnReturn() {
		return redisTestOnReturn;
	}

	public void setRedisTestOnReturn(Boolean redisTestOnReturn) {
		this.redisTestOnReturn = redisTestOnReturn;
	}

	public Integer getRedisMaxIdle() {
		return redisMaxIdle;
	}

	public void setRedisMaxIdle(Integer redisMaxIdle) {
		this.redisMaxIdle = redisMaxIdle;
	}

	public Integer getRedisMinIdle() {
		return redisMinIdle;
	}

	public void setRedisMinIdle(Integer redisMinIdle) {
		this.redisMinIdle = redisMinIdle;
	}

	public Boolean getRedisTestWhileIdle() {
		return redisTestWhileIdle;
	}

	public void setRedisTestWhileIdle(Boolean redisTestWhileIdle) {
		this.redisTestWhileIdle = redisTestWhileIdle;
	}

	public Integer getRedisNumTestsPerEvictionRun() {
		return redisNumTestsPerEvictionRun;
	}

	public void setRedisNumTestsPerEvictionRun(
			Integer redisNumTestsPerEvictionRun) {
		this.redisNumTestsPerEvictionRun = redisNumTestsPerEvictionRun;
	}

	public Integer getRedisTimeBetweenEvictionRunsMillis() {
		return redisTimeBetweenEvictionRunsMillis;
	}

	public void setRedisTimeBetweenEvictionRunsMillis(
			Integer redisTimeBetweenEvictionRunsMillis) {
		this.redisTimeBetweenEvictionRunsMillis = redisTimeBetweenEvictionRunsMillis;
	}

}
