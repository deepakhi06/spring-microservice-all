package com.ds.springconsumer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ds.springconsumer.model.Employee;
import com.ds.springconsumer.proxyservice.EmployeeProducerProxy;

@RestController
public class ConsumerControllerClient {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private EmployeeProducerProxy employeeProducerProxy;

	@GetMapping(value = "/getAll")
	public void getEmployee() throws RestClientException, IOException {

		ServiceInstance serviceInstance = loadBalancer.choose("producer-service");

		System.out.println(serviceInstance.getUri());

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/employee";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	@GetMapping(value = "/getAllEmp")
	public void getAllEmp() throws RestClientException, IOException {
		List<Employee> empList = employeeProducerProxy.finAllEmp();

		System.out.println("=====empList====" + empList);
	}
	
	@GetMapping(value = "/getEmpInfo")
	public void getEmpInfo() throws RestClientException, IOException {
		String empInfo = employeeProducerProxy.getEmpInfo();

		System.out.println("=====emp Info using Feign client====" + empInfo);
	}
}
