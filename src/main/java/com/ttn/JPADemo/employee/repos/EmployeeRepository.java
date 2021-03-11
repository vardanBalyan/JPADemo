package com.ttn.JPADemo.employee.repos;

import com.ttn.JPADemo.employee.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByNameAndLocation(String name, String location);
    List<Employee> findByNameContains(String string);
    List<Employee> findByAgeBetween(int age1, int age2);
    List<Employee> findByNameLike(String stringFormat);
}
