package com.example.springbootlearn02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、创建JavaBeans封装数据
 * 2、整合MyBatis操作数据库
 * 3、整合MyBatis操作数据库
 * 1、配置数据源信息
 * 2、使用注解版的MyBatis
 * 1）、@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 * 步骤：
 * 1、开启基于注解的缓存 @EnableCaching
 * 2、标注缓存注解即可
 *
 * @Cacheable
 * @CacheEvict
 * @CachePut 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在	ConcurrentMap<Object, Object>中
 */
@EnableCaching
@MapperScan("com.example.springbootlearn02.mapper")
/**
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、有自动配置了连接工厂ConnectionFactory
 * 3、RabbitProperties封装了RabbitMQ的配置
 * 4、RabbitTemplate：给RabbitMQ发生和接收消息
 * 5、AmqpAdmin:RabbitMQ系统管理组件
 *      AmqpAdmin：创建和删除Queue、Exchange、Binding
 */
@EnableRabbit // 开启基于注解的RabbitMQ模式
@SpringBootApplication
public class SpringbootLearn02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearn02Application.class, args);
    }

}
