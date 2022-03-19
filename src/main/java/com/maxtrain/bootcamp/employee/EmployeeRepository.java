package com.maxtrain.bootcamp.employee;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	Optional<Employee> findByUnameAndPasswd(String uname, String passwd);

}
