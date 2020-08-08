package com.ds.springconsumer.proxyservice;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds.springconsumer.model.Employee;

@FeignClient(name = "producer-service")
public interface EmployeeProducerProxy {

	@GetMapping("/getAllEmp")
	public List<Employee> finAllEmp();
	
	@GetMapping("/employee")
	public String getEmpInfo();
}
