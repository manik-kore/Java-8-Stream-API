package com.yash.ems.hibernate.dao;

import com.yash.ems.hibernate.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee getEmployeeById(int id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByDept(String deptName);
    void getHighSalariedEmployee();
    List<String> getDeptList();
    void getMaxSalaryOfEmployee();
    List<Employee> getLimitedRecords(int limit);
    double getCompleteExpenditure();
    void addEmployees(Employee emp);
}
