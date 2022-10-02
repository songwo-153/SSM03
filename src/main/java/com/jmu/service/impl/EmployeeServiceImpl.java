package com.jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jmu.mapper.EmployeeMapper;
import com.jmu.pojo.Employee;
import com.jmu.pojo.EmployeeExample;
import com.jmu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 宋石全
 * @version 1.0
 * @Time 2022.10.1
 * @解释:
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    /*分页查询*/
    @Override
    public PageInfo<Employee> testSelectByExamplePage(Integer pageNum) {
        /*开启分页*/
        PageHelper.startPage(pageNum,6);
        List<Employee> list = employeeMapper.selectByExample(null);
        PageInfo<Employee> page = new PageInfo<>(list,5);
        return page;
    }


    /*根据姓名模糊查询*/
    @Override
    public PageInfo<Employee> getEmployeeName(Integer pageNum,String empName) {
        PageHelper.startPage(pageNum,6);
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmpNameLike(empName);
        //employeeExample.createCriteria().andEmpNameNotLike(str);
        List<Employee> list2 = employeeMapper.selectByExample(employeeExample);
        PageInfo<Employee> page1 = new PageInfo<>(list2,5);
        System.out.println("查询结果："+list2);
        return page1;
    }

    @Override
    public int addEmployee(Employee employee) {
        int resoutl = employeeMapper.insert(employee);
        System.out.println("insert结果："+resoutl);
        return resoutl;
    }

    @Override
    public List<Employee> getEmployee(Integer empId) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmpIdEqualTo(empId);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);

        return employees;
    }

    @Override
    public int updataEmployee(Integer empId,Employee employee) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmpIdEqualTo(empId);
        int resoutl = employeeMapper.updateByExampleSelective(employee,employeeExample);
        System.out.println("updateByExample："+resoutl);
        return resoutl;
    }


}
