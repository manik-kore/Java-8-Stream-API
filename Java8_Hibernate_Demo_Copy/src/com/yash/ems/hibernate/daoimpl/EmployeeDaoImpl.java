package com.yash.ems.hibernate.daoimpl;


import com.yash.ems.hibernate.application.EmployeeDemoByStreamAPI;
import com.yash.ems.hibernate.dao.EmployeeDao;
import com.yash.ems.hibernate.exception.*;
import com.yash.ems.hibernate.model.Employee;
import com.yash.ems.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDaoImpl implements EmployeeDao {


    HibernateUtil hibernateUtil;
    public EmployeeDaoImpl()
    {
        hibernateUtil = new HibernateUtil();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> list = session.createQuery("from Employee").getResultList();
        List<Integer> currId = list.stream().map(Employee::getEmpId).collect(Collectors.toList());
        for(Integer idr:currId)
        {
            System.out.println(idr);
        }

        try {

            if (id == 0 || id < 0 || !currId.contains(id)) {
                throw new IdNotValidException("Invalid Id");
            } else {
                return session.get(Employee.class, id);
            }

        } catch (IdNotValidException e) {
            e.printStackTrace();
        }

        return session.get(Employee.class, id);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

                //throw new InvalidNameException("Invalid Name");
                //return session.createQuery("from employee where empName =:name",Employee.class).setParameter("name",name).getResultList();
                List<Employee> empList =session.createQuery("from Employee").getResultList();
                List<Employee> employee =empList.stream().filter(emp->emp.getEmpName().equalsIgnoreCase(name)).collect(Collectors.toList());
                 try
                 {
                     for(Employee emp:employee)
                     {
                         if(emp.getEmpName().matches("^[a-zA-Z]*$"))
                         {
                             System.out.println("Employee Name Is Valid");
                         }
                         else
                         {
                             throw new InvalidNameException("Names Is Invalid");
                         }
                     }
                 }catch (InvalidNameException e)
                 {
                     e.printStackTrace();
                 }
               return employee;

               //return session.createQuery("from Employee where empName =:name",Employee.class).setParameter("name",name).getResultList();

    }

    @Override
    public List<Employee> getEmployeesByDept(String deptName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

                List<Employee> emp =session.createQuery("from Employee").getResultList();
                List<Employee> dept =emp.stream().filter(e->e.getEmpDepartment().equalsIgnoreCase(deptName)).collect(Collectors.toList());
               // dept.forEach(System.out::println);
               try
               {
                   for(Employee d:dept)
                   {
                       if(d.getEmpDepartment().matches("^[a-zA-Z]*$"))
                       {
                           System.out.println("Department Name Is Valid");
                       }
                       else
                       {
                           throw new InvalidDepartmentException("Department Is Not Valid");
                       }
                   }
               }
               catch (InvalidDepartmentException e)
               {
                   e.printStackTrace();
               }
                return dept;

        //return session.createQuery("from Employee where empDepartment =:departmentName",Employee.class).setParameter("departmentName",departmentName).getResultList();

    }

//    @Override
//    public List<Employee> getHighSalariedEmployee(double threshold) {
//        List<Employee> employees = getAllEmployees();
//        return employees.stream()
//                .filter(emp -> emp.getEmpSalary() > threshold)
//                .collect(Collectors.toList());
//    }

    @Override
    public void getHighSalariedEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> emp= session.createQuery("from Employee",Employee.class).getResultList();
        try {
            if (emp != null || !emp.isEmpty()) {
                Employee higestSalariedEmployee = emp.stream().sorted(Comparator.comparing(Employee::getEmpSalary).reversed()).findFirst().get();
                System.out.println("Employee Having Higest Salary Is -: " + higestSalariedEmployee);
            } else {
                throw new EmptyEmployeeListException("Employee List Is Empty");
            }
//        Employee higestSalariedEmployee = employees.stream()
//                .sorted(Comparator.comparing(Employee::getEmpSalary).reversed()).findFirst().get();
//        System.out.println("Employee Having Higest Salary Is -: "+higestSalariedEmployee);
        }
        catch (EmptyEmployeeListException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getDeptList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> deptList = session.createQuery("from Employee",Employee.class).getResultList();
        List<String> dept = deptList.stream().map(Employee::getEmpDepartment).distinct().collect(Collectors.toList());
        try
        {
            if(deptList!=null || !deptList.isEmpty())
            {
                System.out.println("All Departments In Employee Class Are -: "+dept);
                dept.forEach(d-> System.out.println(d));
                //throw new EmptyEmployeeListException("Employee List Is Empty");
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
        return  deptList.stream().map(Employee::getEmpDepartment).distinct().collect(Collectors.toList());

//        return employees.stream()
//                .map(Employee::getEmpDepartment)
//                .distinct()
//                .collect(Collectors.toList());
    }

    @Override
    public void getMaxSalaryOfEmployee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Employee maximumSalary = (Employee) session.createQuery("select max(empSalary) from employee ").getSingleResult();
//        System.out.println(maximumSalary);
        List<Employee> maximumSalary = session.createQuery("from Employee").getResultList();
        try
        {
            if(maximumSalary!=null || !maximumSalary.isEmpty())
            {
                //Optional salary =maximumSalary.stream().max((emp1, emp2)->Double.compare(emp1.getEmpSalary(),emp2.getEmpSalary()));
               OptionalDouble highSalaryAmount = maximumSalary.stream().mapToDouble(s->s.getEmpSalary()).max();
//                if(salary.isPresent())
//                {
//                    System.out.println("Maximum Salary Among All Salaries Is -: "+salary);
//                }
                if(highSalaryAmount.isPresent())
                {
                   // System.out.println("Maximum Salary Among All Salaries Is -: "+highSalaryAmount.getAsDouble());
                    Double high =highSalaryAmount.getAsDouble();
                    if(high!=0 || high>0)
                    {
                        System.out.println("Maximum Salary Among All Salaries Is -: "+high);
                    }
                }
                //throw new EmptyEmployeeListException("Employee List Is Empty");
            }
            else
            {
                throw new InvalidSalaryException("Maximum Salary Not Found");
            }
        }
        catch (InvalidSalaryException e)
        {
            e.printStackTrace();
        }

//        return employees.stream()
//                .max((emp1, emp2) -> Double.compare(emp1.getEmpSalary(), emp2.getEmpSalary()))
//                .orElse(null);
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
//        return employees.stream()
//                .limit(limit)
//                .collect(Collectors.toList());
        return limitedEmployees;
    }

    @Override
    public double getCompleteExpenditure() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("from Employee",Employee.class).getResultList();
        Double expenditure= employees.stream().mapToDouble(Employee::getEmpExpenditure).sum();

        try
        {
            if(expenditure==0 || expenditure<0)
            {
                throw new InvalidExpenditureException("Invalid Employee Expenditure Found");
            }
            else
            {
                return expenditure;
            }
        }
         catch (InvalidExpenditureException e)
         {
             e.printStackTrace();
         }
        return expenditure;


//        return employees.stream()
//                .mapToDouble(Employee::getEmpExpenditure)
//                .sum();
    }

    @Override
    public void addEmployees(Employee employees) {

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


        /*Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();
        ) {

            tx = session.beginTransaction();
            Employee employee = new Employee();
            Scanner sc = new Scanner(System.in);
//            System.out.println("Enter Employee Id");
//            int id = sc.nextInt();
//            employee.setEmpId(id);
            System.out.println("Enter Employee Name");
            String empName = sc.next();
            try
            {
                if(empName.matches("^[a-zA-Z]*$"))
                {
                    //throw new EmployeeNameNotMatchException("Invalid Name");
                    employee.setEmpName(empName);
                }
                else
                {
                    //employee.setEmpName(empName);
                    throw new EmployeeNameNotMatchException("Invalid Name");
                }
            }
            catch (EmployeeNameNotMatchException e)
            {
                  e.printStackTrace();
            }
// ^[a-zA-Z]*$
            System.out.println("Enter Employee Department");
            String empDepartment = sc.next();
            try {
                if (empDepartment.matches("^[a-zA-Z]*$")) {
                    employee.setEmpDepartment(empDepartment);
                } else {
                    throw new InvalidDepartmentException("Department Name Is Invalid");
                }
            }
            catch (InvalidDepartmentException e)
            {
                   e.printStackTrace();
            }


            System.out.println("Enter Employee Salary");
            double empSalary = sc.nextDouble();
            try
            {
                if(empSalary==0 || empSalary<0)
                {
                    //employee.setEmpSalary(empSalary);
                    throw new InvalidSalaryException("Employee Salary Is Not Valid");
                }
                else {
                    employee.setEmpSalary(empSalary);
                    //throw new InvalidSalaryException("Employee Salary Is Not Valid");
                }
            }
            catch (InvalidSalaryException e)
            {
                   e.printStackTrace();
            }

            System.out.println("Enter Expenditure Of Employee");
            double empExpenditure = sc.nextDouble();
            try
            {
                if(empExpenditure==0 || empExpenditure<0)
                {
                    throw new InvalidSalaryException("Employee Expenditure Is Not Valid");
                   // employee.setEmpExpenditure(empExpenditure);
                }
                else {
                    employee.setEmpExpenditure(empExpenditure);
                    //throw new InvalidSalaryException("Employee Salary Is Not Valid");
                }
            }
            catch (InvalidExpenditureException e)
            {
                e.printStackTrace();
            }

            //session.createQuery("INSERT INTO Employee values('empName','empDepartment',empSalary,empExpenditure)");
            session.save(employee);
            tx.commit();
        } catch (HibernateException e) {

            e.printStackTrace();

            }
*/

    }

    @Override
    public List<Employee> findAllEmployees() {
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
                allEmployeeList.forEach(e-> System.out.println(e));
            }
        }
        catch (EmptyEmployeeListException e)
        {
            e.printStackTrace();
        }

        return allEmployeeList;
    }



    // Fetch all employees from the database
//    private List<Employee> getAllEmployees() {
//        List<Employee> employees = new ArrayList<>();
//        try (Connection connection = jdbcUtil.openConnection();
//             Statement stmt =  connection.createStatement()) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
//
//            while (rs.next()) {
//                employees.add(new Employee(
//                        rs.getInt("empId"),
//                        rs.getString("empName"),
//                        rs.getString("empDepartment"),
//                        rs.getDouble("empSalary"),
//                        rs.getDouble("empExpenditure")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employees;
//    }


}
