package org.hibernate.search.test.integration.jtaspring.sessionfactory;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDAO<T, PK extends Serializable> {
	private SessionFactory sessionFactory;
        Class<T> type;
	
 	protected AbstractDAO() {
		// Null constructor for CGLIB
	}

	public AbstractDAO(Class<T> type,
		 SessionFactory sessionFactory) {
		this.type = type;
		this.sessionFactory = sessionFactory;
	}

	public PK save(T obj) {
		return (PK) getSession().save(obj);
	}

        public T read(PK id) {
		T result = (T) getSession().load(type, id);
		return result;
	}
	
	public Transaction beginTransaction() {
		return getSession().beginTransaction();
	}

        protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void setType(Class<T> type) {
		this.type=type;
	}
}
