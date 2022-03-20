package com.maxtrain.bootcamp.expenseline;

import javax.persistence.*;

import com.maxtrain.bootcamp.expense.Expense;
import com.maxtrain.bootcamp.item.Item;

@Entity
public class Expenseline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private int id;
	@Column(columnDefinition="decimal(9,2) NOT NULL")
	private double amount;
	
	//FK
	@ManyToOne(optional=false)
	@JoinColumn(name="expenseId", columnDefinition="int")
	private Expense expense;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="item", columnDefinition="int")
	private Item item;
	
	public Expenseline() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
