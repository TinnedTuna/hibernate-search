/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.hibernate.search.test.integration.jtaspring.sessionfactory;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		this.type = type;
	}
}
