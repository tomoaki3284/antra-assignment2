package com.tomoaki3284.antraAssignment2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeesServiceImpl.class);
	
	private final String url;
	
	private final RestTemplate restTemplate;
	
	public EmployeesServiceImpl(@Value("${app.employee.url}") String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}
	
	private List<Map<String,Object>> getData() {
		Map<String,Object> response = null;
		
		try {
			response = restTemplate.getForObject(url, HashMap.class);
		} catch (HttpClientErrorException ex) {
			throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS);
		}
		
		Object employeesData = response.get("data");
		
		if (employeesData == null) {
			logger.info("url cannot be access url: " + url);
			throw new RuntimeException("data can't be access");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String,Object>> res = mapper.convertValue(employeesData, new TypeReference<>() {});
		
		return res;
	}
	
	public Map<Integer,List<Object>> getEmployeesGroupByAge() {
		List<Map<String,Object>> employees = getData();
		Map<Integer,List<Object>> res = new HashMap<>();
		
		for (Map<String,Object> emp : employees) {
			int age = (int) emp.get("employee_age");
			if (!res.containsKey(age)) {
				res.put(age, new ArrayList<>(List.of(emp)));
			} else {
				res.get(age).add(emp);
			}
		}
		
		return res;
	}
	
	public List<Object> getEmployeesAgeOlderThan(int minAge) {
		List<Map<String,Object>> employees = getData();
		
		return employees
			.stream()
			.filter(emp -> (int) emp.get("employee_age") >= minAge)
			.collect(Collectors.toUnmodifiableList());
	}
}
