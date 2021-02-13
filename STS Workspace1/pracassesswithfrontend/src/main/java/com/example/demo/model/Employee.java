package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Entity(name = "Employee")
@Table(name = "employeetable")
public class Employee {
	
	@Id
	private Integer employeeId;
	
	private String firstname;
	private String lastname;
	private String address;
	private Integer age;
	
	

}
