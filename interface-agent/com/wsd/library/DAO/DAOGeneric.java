package com.wsd.library.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.wsd.library.dbconnection.SessionFactoryProvider;

public class DAOGeneric {
	
	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		return SessionFactoryProvider.getSessionFactory();
	}

	protected Session getCurrentSession() {
		return currentSession;
	}

	protected void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	protected Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	protected void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
}
