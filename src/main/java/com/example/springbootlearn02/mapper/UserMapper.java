package com.example.springbootlearn02.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootlearn02.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
