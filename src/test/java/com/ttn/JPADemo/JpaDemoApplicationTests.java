package com.ttn.JPADemo;

import com.ttn.JPADemo.employee.entities.Employee;
import com.ttn.JPADemo.employee.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaDemoApplicationTests {

	@Autowired
	EmployeeRepository repository;
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate()
	{
		Employee employee = new Employee();
		//employee.setId(1);
		employee.setName("Vardan Balyan");
		employee.setAge(23);
		employee.setLocation("Delhi");

		repository.save(employee);
	}

	@Test
	public void testRead()
	{
		Employee employee = repository.findById(2).get();
		assertNotNull(employee);
		assertEquals("Vardan Balyan",employee.getName());
		System.out.println(">>>>>>>>>>>>>>>> "+employee.getName());
	}

	@Test
	public void testUpdate()
	{
		Employee employee = repository.findById(1).get();
		employee.setName("Vardan");
		repository.save(employee);
	}

	@Test
	public void testDelete()
	{
		if(repository.existsById(1))
			repository.deleteById(1);
	}

	@Test
	public void testCount()
	{
		System.out.println("Total records =============>>>>>>>>>> "+repository.count());
	}

	@Test
	public void testFindByName()
	{
		List<Employee> employees = repository.findByName("Shubham");
		employees.forEach(emp-> System.out.println(emp.getLocation()));
	}

	@Test
	public void testFindByNameAndLocation()
	{
		List<Employee> employees = repository.findByNameAndLocation("Abhay","Haryana");
		employees.forEach(emp-> System.out.println(emp.getAge()));
	}

	@Test
	public void testFindByNameContains()
	{
		List<Employee> employees = repository.findByNameContains("ki");
		employees.forEach(emp-> System.out.println(emp.getName()));
	}

	@Test
	public void testFindByAgeBetween()
	{
		List<Employee> employees = repository.findByAgeBetween(29,32);
		employees.forEach(emp-> System.out.println(emp.getName()));
	}

	@Test
	public void testFindByNameLike()
	{
		List<Employee> employees = repository.findByNameLike("A%");
		employees.forEach(emp-> System.out.println(emp.getName()));
	}

	@Test
	public void testFindAllPaging()
	{
		PageRequest pageable = PageRequest.of(1,2);
		Page<Employee> result = repository.findAll(pageable);
		result.forEach(emp-> System.out.println(emp.getName()));
	}

	@Test
	public void testFindAllSorting()
	{
		repository.findAll(Sort.by(Sort.Direction.DESC,"name","age"))
				.forEach(emp-> System.out.println(emp.getName()));
	}

	@Test
	public void testFindAllSortingAndPaging()
	{
		PageRequest pageable = PageRequest.of(1,3, Sort.Direction.DESC,"age");
		repository.findAll(pageable).forEach(emp-> System.out.println(emp.getName()+" "+emp.getAge()));
	}

	@Test
	public void findAllByInPaging()
	{
		PageRequest pageable = PageRequest.of(0,3);
		List<Employee> employees = repository.findByIdIn(Arrays.asList(2,3,5),pageable);
		employees.forEach(emp-> System.out.println(emp.getId()+" "+emp.getName()));
	}
}

