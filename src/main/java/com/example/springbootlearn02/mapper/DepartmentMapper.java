package com.example.springbootlearn02.mapper;

import com.example.springbootlearn02.bean.Department;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id=#{id}")
    public Department getDeptById(Integer id);

    @Insert("INSERT INTO department(department_name) VALUES(#{departmentName})")
    public int insertDept(Department department);

    @Delete("DELETE FROM department WHERE id=#{id}")
    public int deleteDept(Integer id);

    @Update("UPDATE department SET department_name=#{departmentName} WHERE id=#{id}")
    public int updateDept(Department department);
}
