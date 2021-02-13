package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.Dataunavailableexception;
import com.example.demo.Exceptions.NoSuchEmployeeException;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;


@RestController
@RequestMapping("/api/home")


public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping("/allemployees")
	public List<Employee> getallEmployees() throws Exception
	{
		List list = this.employeeRepo.findAll();
		return Optional.ofNullable(list.isEmpty() ? null : list).orElseThrow(()->new Dataunavailableexception("No Data here"));
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) throws NoSuchEmployeeException
	{
		Optional<Employee> optional= employeeRepo.findById(id);
		
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}
		else
		{
			throw new NoSuchEmployeeException("No such Employee, check again");
		}
	}

	
	@PostMapping
	public Employee addEmployee(@RequestBody @Valid Employee employee) throws NoSuchEmployeeException
	{
		if(employee.getEmployeeId()==null)
		{
			throw new NoSuchEmployeeException("No such Employee, check again");
		}
		return employeeRepo.save(employee);
		
	}
	
	@DeleteMapping("/delete/{Id}")
	public String deleteEmployee(@PathVariable("id") Integer id) throws NoSuchEmployeeException
	{
		Optional<Employee> optional= employeeRepo.findById(id);
		
		if(optional.isPresent())
		{	
			employeeRepo.deleteById(id);
			return "Deleted";
		}
		else
		{
			throw new NoSuchEmployeeException("No such Employee, check again");
		}
	}
		

	@PutMapping("/{Id}")
	public ResponseEntity<Employee> updateAccount(@PathVariable("id") Integer id , @Valid @RequestBody Employee resourceDetails) throws NoSuchEmployeeException {
		Employee account = employeeRepo.findById(id).
				orElseThrow(()->new NoSuchEmployeeException("Not Found"));
		
		account.setFirstname(resourceDetails.getFirstname());
		account.setLastname(resourceDetails.getLastname());
		account.setAddress(resourceDetails.getAddress());
		account.setAge(resourceDetails.getAge());
		
		final Employee newDetails= employeeRepo.save(account);
		return ResponseEntity.ok(newDetails);
		
		
	}
}