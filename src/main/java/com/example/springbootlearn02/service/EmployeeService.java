package com.example.springbootlearn02.service;

import com.example.springbootlearn02.bean.Employee;
import com.example.springbootlearn02.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要用到相同的数据，直接从缓存中获取，不用调用方法
     *
     * 几个属性：
     *   CacheNames/values:指定缓存组件的名字
     *   key:缓存数据用的key，可以用它来指定。默认时使用方法参数的值 1-方法的返回值
     *       编写SpELl  #id：参数id的值 #a0 #p0 #root.args[0]
     *       getEmp[2]
     *   keyGenerator: key的生成器：可以自己指定Key的生成器的组件id
     *      key/keyGenerator：二选一
     *   cacheManager: 指定缓存管理器；或者cacheResolver指定获取解析器
     *
     *   condition: 指定符合条件的情况才缓存
     *      condition = "#id>0"
     *   unless:否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *      unless = "#result == null"
     *      unless = "#a0 == 2":如果第一个参数的值是2.结果不缓存
     *   sync: 是否使用异步模式
     * @param id
     * @return
     */
    @Cacheable(value = {"emp"})
    public Employee getEmpById(Integer id) {
        System.out.println("查询" + id + "号客户");
        return employeeMapper.getEmpById(id);
    }

    public int addEmp(Employee employee){
        return employeeMapper.insertEmp(employee);
    }
}
