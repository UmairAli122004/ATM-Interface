package com.atmInterface.repository;

import com.atmInterface.entity.User;

public interface UserRepository {
	void transfer(int id, String toAcc, double amount);
	double BalanceEnquiry(int pin);
	void withdraw(int id, int amount);
	void changePin(int id, String newPin);
	User findUserByPin(String pin);
}
