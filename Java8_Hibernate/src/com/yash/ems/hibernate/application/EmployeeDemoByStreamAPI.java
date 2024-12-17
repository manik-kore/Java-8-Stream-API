package com.yash.ems.hibernate.application;

import com.yash.ems.hibernate.exception.*;
import com.yash.ems.hibernate.model.Employee;
import com.yash.ems.hibernate.service.EmployeeService;
import com.yash.ems.hibernate.serviceimpl.EmployeeServiceImpl;
import com.yash.ems.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;


public class EmployeeDemoByStreamAPI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
         EmployeeService employeeService = new EmployeeServiceImpl();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add New Employee");
            System.out.println("2. Get Employee by ID");
            System.out.println("3. Get Employees by Name");
            System.out.println("4. Get Employees by Department");
            System.out.println("5. Get High Salaried Employees");
            System.out.println("6. Get Department List");
            System.out.println("7. Get Employee with Max Salary");
            System.out.println("8. Get Limited Records");
            System.out.println("9. Get Complete Expenditure");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Add Employee Into Database");
                    Employee employee = new Employee();
                    employeeService.addEmployee(employee);
                    break;
                case 2:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    try {
                        if (id < 0 || id == 0) {
                            throw new IdNotValidException("Invalid Id");
                        } else {
                            System.out.println(employeeService.getEmployeeById(id));
                        }
                    } catch (IdNotValidException e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    System.out.print("Enter Employee Name: ");
                    String empName = scanner.next();
                    try {
                        if (empName.matches("^[a-zA-Z]*$")) {
                            System.out.println(employeeService.getEmployeesByName(empName));
                        } else {
                            throw new InvalidNameException("Name Is Invalid");
                        }
                    } catch (InvalidNameException e) {
                        e.printStackTrace();
                    }

                    // System.out.println(employeeService.getEmployeesByName(empName));
                    break;
                case 4:
                    System.out.print("Enter Employee Department Name: ");
                    String empDept = scanner.next();
                    try {
                        if (empDept.matches("^[a-zA-Z]*$")) {
                            System.out.println(employeeService.getEmployeesByDept(empDept));
                        } else {
                            throw new InvalidDepartmentException("Department Name Is Not Valid");
                        }
                    } catch (InvalidDepartmentException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    Session session1 = HibernateUtil.getSessionFactory().openSession();
                    List<Employee> highSalariedEmployee = session1.createQuery("from Employee").getResultList();
//                    OptionalDouble highSalaryAmount = highSalariedEmployee.stream().mapToDouble(e -> e.getEmpSalary()).max();
//                    if (highSalaryAmount.isPresent()) {
//                        double highSalary = highSalaryAmount.getAsDouble();
//                    }
//                    try
//                    {
//                    for (Employee e : highSalariedEmployee) {
//                        if (highSalaryAmount.getAsDouble() > e.getEmpSalary()) {
//                            System.out.println(highSalaryAmount.getAsDouble() + " Amount Is A Highest Salary");
//                            employeeService.getHighSalariedEmployee();
//                        } else {
//                            throw new InvalidSalaryException("Invalid Salary Amount");
//                        }
//                    }
//            }catch (InvalidSalaryException e)
//                    {
//                        e.printStackTrace();
//                    }
                    try
                    {
                        if(highSalariedEmployee!=null || !highSalariedEmployee.isEmpty())
                        {
                            employeeService.getHighSalariedEmployee();
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

                    //employeeService.getHighSalariedEmployee();
                    break;
                case 6:
                    System.out.println("List Of All Departments Is");
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    List<String> deptList = session.createQuery("from Employee").getResultList();
                    try
                    {
                        if(deptList!=null || !deptList.isEmpty())
                        {
                            employeeService.getDeptList();
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

                    break;
                case 7:
                    System.out.println("Maximum Salary Among The All Salaries");

                    Session s = HibernateUtil.getSessionFactory().openSession();
                    List<Employee> maxSalaries = s.createQuery("from Employee").getResultList();
                    OptionalDouble highSalaryAmount =maxSalaries.stream().mapToDouble(e->e.getEmpSalary()).max();
                    try
                    {
                        if(highSalaryAmount.isPresent())
                        {
                            Double high =highSalaryAmount.getAsDouble();
                            if(high!=0 || high>0) {
                                employeeService.getMaxSalaryOfEmployee();
                            }
                        }
                        else
                        {
                            throw new InvalidSalaryException("Max Salary Not Found");
                        }
                    }
                    catch (InvalidSalaryException e)
                    {
                        e.printStackTrace();
                    }

                    break;
                case 8:
                    System.out.println("Enter Limit Of Records Of Employee");
                    Session session2 = HibernateUtil.getSessionFactory().openSession();
                    List<String> empList = session2.createQuery("from Employee").getResultList();
                    long count =empList.stream().count();
                    int limit = scanner.nextInt();
                    try
                    {
                        if(limit==0 || limit<0 || limit>count )
                        {
                            throw new InvalidLimitNumberException("Invalid Limit Number");
                            //System.out.println(employeeService.getLimitedRecords(limit));
                        }
                        else
                        {
                            System.out.println(employeeService.getLimitedRecords(limit));
                            //throw new InvalidLimitNumberException("Invalid Limit Number");

                        }
                    }
                    catch (InvalidLimitNumberException e)
                    {
                          e.printStackTrace();
                    }

                    break;
                case 9:
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
                            System.out.println(employeeService.getCompleteExpenditure());
                        }
                    }
                    catch (InvalidExpenditureException e)
                    {
                        e.printStackTrace();
                    }

                   // System.out.println(employeeService.getCompleteExpenditure());
                    break;
                case 10:
                    System.exit(0);

            }

        }
    }
}
