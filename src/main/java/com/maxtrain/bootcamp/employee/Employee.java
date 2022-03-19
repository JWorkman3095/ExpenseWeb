package com.maxtrain.bootcamp.employee;

import javax.persistence.*;

@Entity
//Unique
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames={"uName"}))
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private int id;
	@Column(length=30, nullable=false)
	private String uname;
	@Column(length=30, nullable=false)
	private String passwd;
	@Column(length=30, nullable=false)
	private String fname;
	@Column(length=30, nullable=false)
	private String lname;
	private boolean active;
	
	//default constructor
	public Employee () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	

}
