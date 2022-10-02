package com.jmu.service;

import com.github.pagehelper.PageInfo;
import com.jmu.pojo.Employee;

import java.util.List;

/**
 * @author 宋石全
 * @version 1.0
 * @Time 2022.10.1
 * @解释:
 */
public interface EmployeeService {
//    List<Employee> getEmployeeAll();
//
    /*分页查询*/
    PageInfo<Employee> testSelectByExamplePage(Integer pageNum);
//
//    List<Employee> getEmployeeName(String empName);



    /*根据姓名模糊查询*/
    PageInfo<Employee> getEmployeeName(Integer pageNum,String empName);

    int addEmployee(Employee employee);

    List<Employee> getEmployee(Integer empId);

    int updataEmployee(Integer empId,Employee employee);
}
