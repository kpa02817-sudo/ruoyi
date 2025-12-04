package com.ruoyi.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.dao.SaTokenDaoRedisJackson;

/**
 * Sa-Token Redis 持久化配置
 * 
 * @author ruoyi
 */
@Configuration
public class SaTokenDaoConfig {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 配置 Sa-Token 持久层实现为 Redis
     */
    @Bean
    public SaTokenDao saTokenDao() {
        return new SaTokenDaoRedisJackson();
    }
}
