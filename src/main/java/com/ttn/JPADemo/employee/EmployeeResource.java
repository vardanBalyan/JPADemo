package com.ttn.JPADemo.employee;

import com.ttn.JPADemo.employee.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeResource {

    @Autowired
    EmployeeDaoService service;

    // Get all employees
    @GetMapping(path = "/employee")
    public List<Employee> getAllEmployees()
    {
        return service.fetchAllEmployees();
    }

    // Create an employee
    @PostMapping(path = "/employee")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
    {
        Employee savedEmployee = service.createEmployee(employee);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Delete an employee by id
    @DeleteMapping(path = "/employee/{id}")
    public void deleteAnEmployee(@PathVariable int id)
    {
        Employee employee = service.deleteEmployee(id);
        if(employee==null)
            throw new EmployeeNotFoundException("Employee with given id does not exist");
    }

    // Read an employee by id
    @GetMapping(path = "/employee/id/{id}")
    public Employee readAnEmployeeById(@PathVariable int id)
    {
        Employee employee = service.readAnEmployee(id);
        if(employee == null)
            throw new EmployeeNotFoundException("Employee with given id does not exist");

        return employee;
    }

    // Get total numbers of employees
    @GetMapping(path = "/employee-count")
    public long getTotalCountOfEmployees()
    {
        return service.employeeCount();
    }

    // Get employee by name using finder method
    @GetMapping(path = "/employee/name/{name}")
    public List<Employee> findEmployeeByName(@PathVariable String name)
    {
        return service.findByNameUsingFinderMethod(name);
    }

    // Gets employee having name start with A using finder method
    @GetMapping(path = "/employee-name/start-with-a")
    public List<Employee> getEmployeesWithNameStartWithA()
    {
        return service.employeeStartWithCharA();
    }

    // Get employees having age between 28 and 32
    @GetMapping(path = "/employee-age/between-28-32")
    public List<Employee> getEmployeesAgeBetween28And32()
    {
        return service.findEmployeeByAgeBetween();
    }

    /*
    * url will be :- localhost:8080/employee/page?pageNumber=1&pageSize=2
    * */

    // Sorting and paging all employees
    @GetMapping(path = "/employee/page")
    public List<Employee> getEmployeesPagingAndSorting(@RequestParam int pageNumber,
                                                       @RequestParam int pageSize)
    {
        return service.findAllEmployeeSortingAndPaging(pageNumber,pageSize);
    }

    // Update an employee
    @PatchMapping(path = "/employee/patch")
    public void updateAnEmployee(@RequestBody Employee employee, @RequestParam int id)
    {
        Employee updatedEmployee = service.updateEmployee(employee, id);
        if(updatedEmployee == null)
            throw new EmployeeNotFoundException("Employee with given id does not exist");
    }
}
