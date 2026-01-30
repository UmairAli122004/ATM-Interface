package com.atmInterface.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
@Entity
@Cacheable
@Table(
		name="user_accounts",
	    indexes = {
	        @Index(name = "idx_user_pin", columnList = "atm_pin")
	    }
	)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="full_name", nullable=false)
	private String name;
	@Column(name="amount", nullable=false)
	private double amount;
	@Column(name="atm_pin", nullable=false, unique=true)
	private String pin;
	@Column(name="acc_number", nullable=false, unique=true)
	private String accountNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

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
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}
}
