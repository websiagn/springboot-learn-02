package com.example.springbootlearn02;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.example.springbootlearn02.bean.Book;
import com.example.springbootlearn02.bean.Employee;
import com.example.springbootlearn02.bean.User;
import com.example.springbootlearn02.mapper.EmployeeMapper;
import com.example.springbootlearn02.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootLearn02ApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate; // k-v都是对象

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("----测试mybatis-plus-----");
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

//    @Autowired
//    RedisTemplate<Object,Employee> empRedisTemplate;

    @Test
    void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

    /**
     * Redis常见的五大数据类型
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue()[String（字符串）]
     * stringRedisTemplate.opsForList()[List（列表）]
     * stringRedisTemplate.opsForSet()[Set（集合）]
     * stringRedisTemplate.opsForHash()[Hash（散列）]
     * stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    void test01(){
//       stringRedisTemplate.opsForValue().append("msg","hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");

    }

    @Test
    void test02(){
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-01",empById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
        redisTemplate.opsForValue().set("emp-01", emp);
    }


    // 测试RabbitMQ
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 单播（点对点）
     */
    @Test
    public void testAmqp(){
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("He",123,true));
        // 对象默认序列化以后发生出去
        rabbitTemplate.convertAndSend("exchange.direct","baidu.news",new Book("西游记","吴承恩"));
    }


    /**
     * 接收消息
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("baidu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void broadcast(){
        rabbitTemplate.convertAndSend("exchange.fanout","baidu",new Book("西游记","吴雪琴"));
    }
}
