package com.example.springbootlearn02;

import com.example.springbootlearn02.bean.Employee;
import com.example.springbootlearn02.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    public int addEmp(Employee employee){
        return employeeMapper.insertEmp(employee);
    }
}
