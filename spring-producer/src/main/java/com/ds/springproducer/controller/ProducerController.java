package com.ds.springproducer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.springproducer.model.Employee;

@RestController
@RefreshScope
public class ProducerController {
	
	@GetMapping(value ="/getAllEmp")
	public List<Employee> getListOfEmp(){
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee("deepak",30,"noida"));
		list.add(new Employee("deepak1",30,"noida"));
		list.add(new Employee("deepak2",30,"noida"));
		return list;
		
	}
	@GetMapping(value ="/employee")
	public String getEmpName(){
		return "Hello deepak";
		
	}
}
