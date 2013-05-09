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
@ContextConfiguration(locations={"classpath*:session-factory-beans.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class DoughnutDAOTest {
	@Inject
	private DoughnutDAO doughnutDAO;
	
	@Test
	public void test() {
		Transaction tx = doughnutDAO.beginTransaction();
		try {
			Doughnut d = new Doughnut("Test");
			Long doughnutId = doughnutDAO.save(d);
			d = doughnutDAO.read(doughnutId);
			List<Doughnut> doughnuts = doughnutDAO.findByKind("Test");
			assert doughnuts != null;
			assert doughnuts.size() == 1;
			assert doughnuts.contains(d);
		} finally {
			tx.rollback();	
		}
	}
}
