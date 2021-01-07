package com.atsuwell.springbootweb.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.atsuwell.springbootweb.dao.DepartmentDao;
import com.atsuwell.springbootweb.dao.EmployeeDao;
import com.atsuwell.springbootweb.entities.Department;
import com.atsuwell.springbootweb.entities.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工
     *
     * @return
     */
    @GetMapping("/emps")
    public String getList(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
//        查询所有部门信息 在员工添加界面的下拉显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 员工添加界面
     *
     * @return
     */
    @PostMapping(value = "/emp")
    public String toAddUser(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 编辑按钮
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toAddPage(@PathVariable("id") Integer id, Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        return "emp/add";
    }

    /**
     * 修改
     * @param employee
     * @return
     */
    @PutMapping(value = "/emp")
    public String updateUser(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /**
     * 删除
     */
    @DeleteMapping(value = "/emp/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
