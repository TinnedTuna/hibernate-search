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

import javax.inject.Inject;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:session-factory-beans.xml" } )
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
@Transactional
public class DoughnutDAOTest {
	@Inject
	private DoughnutDAO doughnutDAO;

	@Test
	public void test() {
		Transaction tx = doughnutDAO.beginTransaction();
		try {
			Doughnut d = new Doughnut("Test");
			Long doughnutId = doughnutDAO.save( d );
			d = doughnutDAO.read( doughnutId );
			List<Doughnut> doughnuts = doughnutDAO.findByKind( "Test" );
			assert doughnuts != null;
			assert doughnuts.size() == 1;
			assert doughnuts.contains( d );
		}
		finally {
			tx.rollback();
		}
	}
}
