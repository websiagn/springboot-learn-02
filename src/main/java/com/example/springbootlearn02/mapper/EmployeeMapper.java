package com.example.springbootlearn02.mapper;

import com.example.springbootlearn02.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id=#{id}")
    public Employee getEmpById(Integer id);

    @Insert("INERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public int insertEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public int deleteEmp(Integer id);

    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public int updateEmp(Employee employee);
}
