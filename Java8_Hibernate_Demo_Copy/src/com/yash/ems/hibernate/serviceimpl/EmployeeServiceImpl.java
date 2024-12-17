package com.yash.ems.hibernate.serviceimpl;


import com.yash.ems.hibernate.daoimpl.EmployeeDaoImpl;
import com.yash.ems.hibernate.exception.*;
import com.yash.ems.hibernate.model.Employee;
import com.yash.ems.hibernate.service.EmployeeService;
import com.yash.ems.hibernate.util.HibernateUtil;
import org.hibernate.Session;


import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDaoImpl employeeDAO = new EmployeeDaoImpl();

    @Override
    public Employee getEmployeeById(int id) {
        try {
            if (id == 0 || id < 0) {
                throw new IdNotValidException("Invalid Id");
            } else {

                return employeeDAO.getEmployeeById(id);
            }

        } catch (IdNotValidException e) {
            e.printStackTrace();
        }
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {

        try {
            if (name.matches("^[a-zA-Z]*$")) {

               return employeeDAO.getEmployeesByName(name);
            } else {
                throw new InvalidNameException("Name Is Invalid");
            }

        } catch (InvalidNameException e) {
            e.printStackTrace();
        }

        return employeeDAO.getEmployeesByName(name);
    }

    @Override
    public List<Employee> getEmployeesByDept(String deptName) {

        try
        {
            if(deptName.matches("^[a-zA-Z]*$"))
            {
                return employeeDAO.getEmployeesByDept(deptName);
            }
            else
            {
                throw new InvalidDepartmentException("Department Name Is Not Valid");
            }
        }
        catch (InvalidDepartmentException e)
        {
            e.printStackTrace();
        }

        return employeeDAO.getEmployeesByDept(deptName);
    }

    @Override
    public void getHighSalariedEmployee() {

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        List<Employee> highSalariedEmployee = session1.createQuery("from Employee").getResultList();
        try
        {
            if(highSalariedEmployee!=null || !highSalariedEmployee.isEmpty())
            {
                employeeDAO.getHighSalariedEmployee();
            }
            else
            {
                throw new EmptyEmployeeListException("No Such Employee Found Having Highest Salary");
            }
        }
        catch (EmptyEmployeeListException e)
        {
            e.printStackTrace();
        }

       // employeeDAO.getHighSalariedEmployee();

    }

    @Override
    public List<String> getDeptList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> deptList = session.createQuery("from Employee",Employee.class).getResultList();
        List<String> dept = deptList.stream().map(Employee::getEmpDepartment).distinct().collect(Collectors.toList());
        try
        {
            if(dept!=null || !dept.isEmpty())
            {
                return employeeDAO.getDeptList();
            }
            else
            {
                throw new DepartmentListNotFoundException("Department Not Found");
            }
        }
        catch (DepartmentListNotFoundException e)
        {
              e.printStackTrace();
        }
        return employeeDAO.getDeptList();
    }

    @Override
    public void getMaxSalaryOfEmployee() {
        try {
            employeeDAO.getMaxSalaryOfEmployee();

        }
        catch (InvalidSalaryException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getLimitedRecords(int limit) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("from Employee").getResultList();
        List<Employee> limitedEmployees =employees.stream().limit(limit).collect(Collectors.toList());
        long count =employees.stream().count();
        try
        {
            if(limit!=0 || limit<0 || limit==count)
            {
                System.out.println("Limited Empoyees Are -: "+limitedEmployees);
                limitedEmployees.forEach(e-> System.out.println(e));
                return employeeDAO.getLimitedRecords(limit);
                //throw new EmptyEmployeeListException("Employee List Is Empty");
            }
            else
            {
                throw new InvalidLimitNumberException("Invalid Limit Number");
            }
        }
        catch (InvalidLimitNumberException e)
        {
            e.printStackTrace();
        }

        return employeeDAO.getLimitedRecords(limit);
    }

    @Override
    public double getCompleteExpenditure() {

        System.out.println("Total Expenditure Of All Employee Is");
        Session session3 = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session3.createQuery("from Employee",Employee.class).getResultList();
        Double expenditure= employees.stream().mapToDouble(Employee::getEmpExpenditure).sum();

        try
        {
            if(expenditure==0 || expenditure<0)
            {
                throw new InvalidExpenditureException("Invalid Employee Expenditure Found");
            }
            else
            {
                return employeeDAO.getCompleteExpenditure();
            }
        }
        catch (InvalidExpenditureException e)
        {
            e.printStackTrace();
        }

        return employeeDAO.getCompleteExpenditure();
    }

    @Override
    public void addEmployee(Employee employees)
    {
//        try
//        {
//            if(employee.getEmpName()!=null || employee.getEmpDepartment()!=null || employee.getEmpSalary()!=0 || employee.getEmpExpenditure()!=0)
//            {
//                employeeDAO.addEmployees(employee);
//            }
//            else
//            {
//                throw new EmployeeNotFoundException("Employee Not Found");
//            }
//        }
//        catch (EmployeeNotFoundException e)
//        {
//            e.printStackTrace();
//        }

        try {
            if (employees.getEmpName().matches("^[a-zA-Z]*$")) {
                employees.setEmpName(employees.getEmpName());
            } else {
                throw new EmployeeNameNotMatchException("Invalid Employee Name");
            }
        }
        catch (EmployeeNameNotMatchException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(employees.getEmpDepartment().matches("^[a-zA-Z]*$"))
            {
                employees.setEmpDepartment(employees.getEmpDepartment());
            }
            else
            {
                throw new InvalidDepartmentException("Department Name Is Invalid");
            }
        }
        catch (InvalidDepartmentException e)
        {
            e.printStackTrace();
        }

        try
        {
            if(employees.getEmpSalary()==0 || employees.getEmpSalary()<0)
            {
                throw new InvalidSalaryException("Employee Salary Is Not Valid");
            }
            else
            {
                employees.setEmpSalary(employees.getEmpSalary());
            }
        }
        catch (InvalidSalaryException e)
        {
            e.printStackTrace();
        }

        try
        {
            if(employees.getEmpExpenditure()==0 || employees.getEmpExpenditure()<0)
            {
                throw new InvalidExpenditureException("Employee Expenditure Is Not Valid");
            }
            else
            {
                employees.setEmpExpenditure(employees.getEmpExpenditure());
            }
        }
        catch (InvalidExpenditureException e)
        {
            e.printStackTrace();
        }

        employeeDAO.addEmployees(employees);
    }

    @Override
    public List<Employee> findAllEmployess() {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        List<Employee> allEmployeeList =session1.createQuery("from Employee").getResultList();
        try
        {
            if(allEmployeeList.isEmpty())
            {
                throw new EmptyEmployeeListException("Empty Employee List Is There");
            }
            else
            {
                employeeDAO.findAllEmployees();
            }
        }
        catch (EmptyEmployeeListException e)
        {
            e.printStackTrace();
        }

        return allEmployeeList;
    }
}
