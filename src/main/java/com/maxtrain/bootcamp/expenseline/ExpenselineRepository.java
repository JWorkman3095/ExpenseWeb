package com.maxtrain.bootcamp.expenseline;

import org.springframework.data.repository.CrudRepository;

public interface ExpenselineRepository extends CrudRepository <Expenseline, Integer>{
	
	Iterable<Expenseline> findbyRequestID(int requestId);

}
