package com.example.reactiverestapp.dto;

import com.example.reactiverestapp.model.Department;
import com.example.reactiverestapp.model.User;

public class UserDepartmentDTO {
    private Integer userId;
    private String userName;
    private int age;
    private double salary;
    private Integer departmentId;
    private String departmentName;
    private String loc;

    public UserDepartmentDTO(User user, Department department) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.age = user.getAge();
        this.salary = user.getSalary();

        this.departmentId = department.getId();
        this.departmentName = department.getName();
        this.loc = department.getLoc();
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getLoc() {
        return loc;
    }
}
