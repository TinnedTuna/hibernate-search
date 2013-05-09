package org.hibernate.search.test.integration.jtaspring.sessionfactory;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;


@Repository
public class DoughnutDAO extends AbstractDAO<Doughnut,Long> {

	public DoughnutDAO() {
		this.setType(Doughnut.class);
	}

	public DoughnutDAO(SessionFactory sf) {
		super(Doughnut.class, sf);
	}

	public List<Doughnut> findByKind(String kind) {
		Criteria c = getSession().createCriteria(Doughnut.class)
			.add(Restrictions.eq("kind", kind));
		return c.list();

	}
}
