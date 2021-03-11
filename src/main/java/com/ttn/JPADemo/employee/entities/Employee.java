package com.ttn.JPADemo.employee.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
public class Employee {
    private String name;

    /* for table type id generator*/
    //@TableGenerator(name = "employeeIdGeneratorTable", table="employee", pkColumnName = "id",
    //valueColumnName = "name", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "employeeIdGeneratorTable")

    /* for custom random id generator*/
    //@GenericGenerator(name="empid", strategy="com.ttn.JPADemo.employee.CustomRandomIdGenerator")
    //@GeneratedValue(generator = "empid")

    @Id
    /*for identity type id generator*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
