package com.example.springbootlearn02.service;

import com.example.springbootlearn02.bean.Employee;
import com.example.springbootlearn02.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     *  运行流程：
     * @Cacheable:
     * 1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名称获取：
     * （CacheManager先去获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建
     * 2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数
     *      key是按照某种策略生成的：默认是使用KeyGenerator生成的，默认使用SimpleKeyGenerator
     *          SimpleKeyGenerator生成Key的默认策略：
     *              如果没有参数：key=new SimpleKey()
     *              如果有一个参数：key=参数的值
     *              如果有多个参数：key=new SimpleKey(params)
     * 3、没有查到缓存就调用目标方法
     * 4、将目标方法返回的结果，放进缓存中
     *
     * @Cacheable标记的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存
     * 如果没有就运行方法并将结果放入缓存：以后再来调用亏可以直接使用缓存中的数据；
     *
     * 核心：
     *  1）、使用CacheManager【ConcurrentMapCacheManager默认】按照名字得到Cache【ConcurrentMapCache默认】组件
     *  2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     */

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
     *   sync: 是否使用同步模式
     * @param id
     * @return
     */
//    @Cacheable(value = {"emp"}, key = "#root.methodName + '[' + #id + ']'")  // 使用指定的keyGenerator
//    @Cacheable(cacheNames ={"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0>1", unless = "#a0==2")
    @Cacheable(value = {"emp"})
    public Employee getEmpById(Integer id) {
        System.out.println("查询" + id + "号客户");
        return employeeMapper.getEmpById(id);
    }

    public int addEmp(Employee employee){
        return employeeMapper.insertEmp(employee);
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据，同步更新缓存
     * 修改了数据库某个数据，同时更新缓存
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     *   @Cacheable的key是不能用#result
     * @param employee
     * @return
     */
    @CachePut(value = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee){
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * key:指定要删除的缓存数据 key
     * allEntries:指定删除所有缓存数据
     * beforeInvocation: 在方法执行前是否执行清除缓存
     * @param id
     */
    @CacheEvict(value = "emp", key = "#id"/*, beforeInvocation = true*//* allEntries = true*/)
    public void deleteEmp(Integer id){
        System.out.println("删除" + id + "号员工");
//        employeeMapper.deleteEmp(id);
    }
}
