package com.maxtrain.bootcamp.item;

import javax.persistence.*;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private int id;
	@Column(length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(9,2) NOT NULL")
	private double limits;
	
	public Item () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLimit() {
		return limits;
	}

	public void setLimit(double limits) {
		this.limits = limits;
	}
	
	
	
	

}
