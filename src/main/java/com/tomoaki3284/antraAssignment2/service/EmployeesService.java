package com.tomoaki3284.antraAssignment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EmployeesService {
	public Map<Integer, List<Object>> getEmployeesGroupByAge();
	
	public List<Object> getEmployeesAgeOlderThan(int minAge);
}
