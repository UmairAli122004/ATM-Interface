package com.atmInterface.repositoryImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.atmInterface.config.DatabaseConfig;
import com.atmInterface.entity.User;
import com.atmInterface.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	private final SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();
	@Override
	public void transfer(int id, String toAcc, double amount) {
	    try(Session session = sessionFactory.openSession()) {
	        Transaction transaction = session.beginTransaction();
     
	        User userFrom = session.find(User.class, id);
	        User userTo = session.createSelectionQuery("FROM User U WHERE U.accountNumber = :acc", User.class)
	                             .setParameter("acc", toAcc)
	                             .getSingleResultOrNull();

	        if(userFrom == null || userTo == null) {
	            throw new RuntimeException("Invalid account number");
	        }

	        if(userFrom.getAmount() < amount) {
	            throw new RuntimeException("Insufficient balance in source account");
	        }

	        // Deduct from sender
	        userFrom.setAmount(userFrom.getAmount() - amount);

	        // Add to receiver
	        userTo.setAmount(userTo.getAmount() + amount);

	        // Update both
	        session.merge(userFrom);
	        session.merge(userTo);

	        transaction.commit();
	    }
	}



	@Override
	public double BalanceEnquiry(int pin) {
	    String hql = "SELECT U.amount FROM User U WHERE U.pin = :pin";
	    try (Session session = sessionFactory.openSession()) {
	        Double balance = session.createSelectionQuery(hql, Double.class)
	                .setParameter("pin", pin)
	                .setCacheable(false)
	                .getSingleResultOrNull();
	        return balance != null ? balance : 0.0;
	    }
	}


	@Override
	public void withdraw(int id, int amount) {
		try(Session session = sessionFactory.openSession()){
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, id);
			if(user.getAmount() < amount) {
				throw new RuntimeException("Insufficient balance in your Account.");
			}
			user.setAmount(user.getAmount()-amount);
			session.merge(user);
			transaction.commit();
		}
	}

	@Override
	public void changePin(int id, String newPin) {
		try(Session session = sessionFactory.openSession()){
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, id);
			user.setPin(newPin);
			session.merge(user);
			transaction.commit();
		}
	}

	@Override
	public User findUserByPin(String pin) {
		String hql = "FROM User U WHERE U.pin = :pin";
		try(Session session = sessionFactory.openSession()){
			return session.createSelectionQuery(hql, User.class).setParameter("pin", pin).setCacheable(true).getSingleResultOrNull();
		}
	}
	
}
