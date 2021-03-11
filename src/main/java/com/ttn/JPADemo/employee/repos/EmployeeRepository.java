package com.ttn.JPADemo.employee.repos;

import com.ttn.JPADemo.employee.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    List<Employee> findByNameAndLocation(String name, String location);
    List<Employee> findByNameContains(String string);
    List<Employee> findByAgeBetween(int age1, int age2);
    List<Employee> findByNameLike(String stringFormat);
    List<Employee> findByIdIn(List<Integer> ids, Pageable pageable);
}
