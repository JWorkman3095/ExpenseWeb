package com.maxtrain.bootcamp.expenseline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.bootcamp.expense.ExpenseRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/expenselines")
public class ExpenselineController {
	
	@Autowired
	private ExpenselineRepository explRepo;
	@Autowired
	private ExpenseRepository expRepo;
	
	
	@SuppressWarnings("rawtypes")
	private ResponseEntity recalcExpenseTotal(int expenseId) {
		var expOpt = expRepo.findById(expenseId);
		if(expOpt.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		var expense = expOpt.get();
		var expenseTotal = 0;
		for(var expenseline : expense.getExpenselines()) {
			expenseTotal += expenseline.getItem().getLimit()
		}
		expense.setTotal(expenseTotal);
		expRepo.save(expense);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// ALL
	@GetMapping
	public ResponseEntity<Iterable<Expenseline>> getExpenselines() {
		var expenselines = explRepo.findAll();
		return new ResponseEntity<Iterable<Expenseline>>(expenselines, HttpStatus.OK);
	}
	
	//PK
	@GetMapping("{id}")
	public ResponseEntity<Expenseline> getExpenseline(@PathVariable int id) {
		var expl = explRepo.findById(id);
		if (expl.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expenseline>(expl.get(), HttpStatus.OK);
	}
	
	// POST - ADD
	@PostMapping
	public ResponseEntity<Expenseline> postExpenseline(@RequestBody Expenseline expenseline) throws Exception {
		if(expenseline == null || expenseline.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var expl = explRepo.save(expenseline);
		var explEntity = this.recalcRequestTotal(expl.getExpense() .getId());
		if(explEntity.getSt)
	}
	
}
