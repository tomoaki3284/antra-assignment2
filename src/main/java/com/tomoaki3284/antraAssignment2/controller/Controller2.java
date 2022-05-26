package com.tomoaki3284.antraAssignment2.controller;

import com.tomoaki3284.antraAssignment2.service.EmployeesService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
public class Controller2 {
	
	private final EmployeesService employeesService;
	
	@Autowired
	public Controller2(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getEmployeesAgeOlderThan() {
		Map<Integer,List<Object>> res = employeesService.getEmployeesGroupByAge();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
