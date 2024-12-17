package com.yash.ems.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int empId;

    @Column(name = "empName")
    private String empName;

    @Column(name = "empDepartment")
    private String empDepartment;

    @Column(name = "empSalary")
    private double empSalary;

    @Column(name = "empExpenditure")
    private double empExpenditure;

    public Employee() {
    }

    public Employee(int empId, String empName, String empDepartment, double empSalary, double empExpenditure) {
        this.empId = empId;
        this.empName = empName;
        this.empDepartment = empDepartment;
        this.empSalary = empSalary;
        this.empExpenditure = empExpenditure;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public double getEmpExpenditure() {
        return empExpenditure;
    }

    public void setEmpExpenditure(double empExpenditure) {
        this.empExpenditure = empExpenditure;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empDepartment='" + empDepartment + '\'' +
                ", empSalary=" + empSalary +
                ", empExpenditure=" + empExpenditure +
                '}';
    }
}
