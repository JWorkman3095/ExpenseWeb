package com.maxtrain.bootcamp.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
//sending and receiving json
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expRepo;
	
	//ALL
	@GetMapping
	public ResponseEntity<Iterable<Expense>> getExpense() {
		var e = expRepo.findAll();
		return new ResponseEntity<Iterable<Expense>>(e, HttpStatus.OK);
	}
	
	//PK
	@GetMapping("{id}")
	public ResponseEntity<Expense> getExpense(@PathVariable int id) {
		var e = expRepo.findById(id);
		if (e.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(e.get(), HttpStatus.OK);
	}
	//Post / Add
	@PostMapping
	public ResponseEntity<Expense> postExpense(@RequestBody Expense expense) {
		if (expense == null || expense.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var ex = expRepo.save(expense);
		return new ResponseEntity<Expense>(ex, HttpStatus.CREATED);
	}
	//Put - Modify
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putExpense(@PathVariable int id, @RequestBody Expense expense) {
		if (expense == null || expense.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var exp = expRepo.findById(expense.getId());
		if (exp.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		expRepo.save(expense);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteExpense(@PathVariable int id) {
		var exp = expRepo.findById(id);
		if (exp.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		expRepo.delete(exp.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
