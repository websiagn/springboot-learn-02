package com.example.springbootlearn02.service;

import com.example.springbootlearn02.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = {"baidu.news"})
    public void listener(Book book){
        System.out.println("收到消息" + book);
    }

//    @RabbitListener(queues = {"baidu.new"})
//    public void recerive(Message message){
//        System.out.println(message.getBody());
//    }
}
