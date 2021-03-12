package com.ttn.JPADemo.employee;

import com.ttn.JPADemo.employee.entities.Employee;
import com.ttn.JPADemo.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeDaoService {
    @Autowired
    EmployeeRepository repository;

    public List<Employee> fetchAllEmployees()
    {
        List<Employee> employeeList = new ArrayList<>();
        repository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    public void createEmployee(Employee employee)
    {
        repository.save(employee);
    }

    public Employee readAnEmployee(int id)
    {
        if(repository.existsById(id))
        {
            Employee employee = repository.findById(id).get();
            return employee;
        }
        return null;
    }


    public Employee updateEmployee(Employee employee, int id)
    {
        if(repository.existsById(id))
        {
            Employee emp = repository.findById(id).get();
            emp.setName(employee.getName());
            emp.setLocation(employee.getLocation());
            emp.setAge(employee.getAge());
            repository.save(emp);
            return emp;
        }
        return null;
    }


    public Employee deleteEmployee(int id)
    {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
            return new Employee();
        }
        return null;
    }


    public long employeeCount()
    {
        return repository.count();
    }


    public List<Employee> findByNameUsingFinderMethod(String name)
    {
        List<Employee> employees = repository.findByName(name);
        return employees;
    }


    public List<Employee> findEmployeeByAgeBetween()
    {
        List<Employee> employees = repository.findByAgeBetween(29,32);
        return employees;
    }

    public List<Employee> employeeStartWithCharA()
    {
        List<Employee> employees = repository.findByNameLike("A%");
        return employees;
    }

    public List<Employee> findAllEmployeeSortingAndPaging(int pageNumber, int pageSize)
    {
        PageRequest pageable = PageRequest.of(pageNumber,pageSize, Sort.Direction.DESC,"age");
        Page<Employee> pageContents = repository.findAll(pageable);
        List<Employee> employees = pageContents.getContent();
        return employees;
    }

    public void testFindByNameAndLocation()
    {
        List<Employee> employees = repository.findByNameAndLocation("Abhay","Haryana");
        employees.forEach(emp-> System.out.println(emp.getAge()));
    }


    public void testFindByNameContains()
    {
        List<Employee> employees = repository.findByNameContains("ki");
        employees.forEach(emp-> System.out.println(emp.getName()));
    }


    public void testFindAllPaging()
    {
        PageRequest pageable = PageRequest.of(1,2);
        Page<Employee> result = repository.findAll(pageable);
        result.forEach(emp-> System.out.println(emp.getName()));
    }


    public void testFindAllSorting()
    {
        repository.findAll(Sort.by(Sort.Direction.DESC,"name","age"))
                .forEach(emp-> System.out.println(emp.getName()));
    }


    public void findAllByInPaging()
    {
        PageRequest pageable = PageRequest.of(0,3);
        List<Employee> employees = repository.findByIdIn(Arrays.asList(2,3,5),pageable);
        employees.forEach(emp-> System.out.println(emp.getId()+" "+emp.getName()));
    }
}
