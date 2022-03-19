package com.maxtrain.bootcamp.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
// sending and receiving json
@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

		@Autowired
		private EmployeeRepository employeeRepo;
		
		//Login
		@GetMapping("{uname}/{passwd}")
		public ResponseEntity<Employee> login(@PathVariable String uname, @PathVariable String passwd) {
			var unm = employeeRepo.findByUnameAndPasswd(uname, passwd);
			if(unm.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Employee>(unm.get(), HttpStatus.OK);
		}
		//ALL
		@GetMapping 
		public ResponseEntity<Iterable<Employee>> getEmployee() {
			var empl = employeeRepo.findAll();
			return new ResponseEntity<Iterable<Employee>>(empl, HttpStatus.OK);
		}
		
		//PK
		@GetMapping("{id}")
		public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
			var empl = employeeRepo.findById(id);
			if (empl.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Employee>(empl.get(), HttpStatus.OK);			
		}
		// Post - ADD
		@PostMapping
		public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
			if (employee == null || employee.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var emp = employeeRepo.save(employee);
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		}
		//Put - Modify
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity putEmployee(@PathVariable int id, @RequestBody Employee employee) {
			if (employee == null || employee.getId() == 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var emp = employeeRepo.findById(employee.getId());
			if(emp.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			employeeRepo.save(employee);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		}
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity deleteEmployee(@PathVariable int id) {
			var epl = employeeRepo.findById(id);
			if (epl.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			employeeRepo.delete(epl.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}
