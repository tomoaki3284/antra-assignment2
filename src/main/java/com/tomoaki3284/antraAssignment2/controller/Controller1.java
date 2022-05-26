package com.tomoaki3284.antraAssignment2.controller;

import com.tomoaki3284.antraAssignment2.service.EmployeesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api1")
public class Controller1 {
	
	private final EmployeesService employeesService;
	
	@Autowired
	public Controller1(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}
	
	@GetMapping("/{age}")
	public ResponseEntity<?> getEmployeesAgeOlderThan(@PathVariable int age) {
		List<Object> res = employeesService.getEmployeesAgeOlderThan(age);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
