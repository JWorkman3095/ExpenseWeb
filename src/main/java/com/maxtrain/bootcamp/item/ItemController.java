package com.maxtrain.bootcamp.item;

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
//sending and receiving json
@RestController
@RequestMapping("/api/items")

public class ItemController {
	
	@Autowired
	private ItemRepository itemRepo;
			
	//ALL
	@GetMapping 
	public ResponseEntity<Iterable<Item>> getItem() {
		var itm = itemRepo.findAll();
		return new ResponseEntity<Iterable<Item>>(itm, HttpStatus.OK);
	}
	//PK
	@GetMapping("{id}")
	public ResponseEntity<Item> getItem(@PathVariable int id) {
		var itm = itemRepo.findById(id);
		if (itm.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(itm.get(), HttpStatus.OK);
	}
	// Post - ADD
	@PostMapping
	public ResponseEntity<Item> postItem(@RequestBody Item item) {
		if (item == null || item.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var itm = itemRepo.save(item);
		return new ResponseEntity<Item>(itm, HttpStatus.CREATED);
	}
	//Put - Modify
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putItem(@PathVariable int id, @RequestBody Item item) {
		if (item == null || item.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var i = itemRepo.findById(item.getId());
		if (i.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		itemRepo.save(item);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteItem(@PathVariable int id) {
		var itm = itemRepo.findById(id);
		if (itm.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		itemRepo.delete(itm.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}