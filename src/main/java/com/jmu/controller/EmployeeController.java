package com.jmu.controller;

import com.github.pagehelper.PageInfo;
import com.jmu.pojo.Employee;
import com.jmu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 宋石全
 * @version 1.0
 * @Time 2022.10.1
 * @解释:
 */


/**
 * 查询所有员工信息：/employee         Get
 * 查询员工分页信息：/employee/page/1  Get
 * 根据id查询员工： /employee/1       Get
 * 添加员工信息     /employee         post
 * 修改员工信息     /employee         put
 * 删除员工信息     /employee         delete
 *
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /*分页查询*/
    @RequestMapping( value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String testSelectByExamplePage(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageInfo<Employee> page = employeeService.testSelectByExamplePage(pageNum);
        model.addAttribute("page" ,page);
        return "employee-list";
    }

    /*根据姓名模糊查询*/
    @RequestMapping(value = "/employee/select/{pageNum}", method = RequestMethod.GET)
    public String getEmployeeByName(@PathVariable("pageNum") Integer pageNum,String empName, Model model) {
        System.out.println("员工名字"+empName);
        if(empName == null || empName.equals("")){
            return "redirect:/employee/page/1";
        }else {
            PageInfo<Employee> page1 = employeeService.getEmployeeName(pageNum,empName);
            model.addAttribute("page1" ,page1);
            return "employee-select";
        }
    }



    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        System.out.println("表单提交数据："+employee);
        int resoult = employeeService.addEmployee(employee);
        return "redirect:/employee/page/1";
    }

    /*更新*/
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.GET)
    public String toUpade(@PathVariable("empId") Integer empId,Model model) {
        List<Employee> employee = employeeService.getEmployee(empId);
        Employee employee1 = employee.get(0);
        System.out.println("employee1"+employee1);
        model.addAttribute("employee",employee1);
        return "employee-updata";
    }

    /*更新*/
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.PUT)
    public String updataEmployee(@PathVariable("empId") Integer empId,Employee employee) {
        System.out.println("表单提交数据："+employee+"id="+empId);
        int resoult = employeeService.updataEmployee(empId,employee);
        return "redirect:/employee/page/1";
    }
}
