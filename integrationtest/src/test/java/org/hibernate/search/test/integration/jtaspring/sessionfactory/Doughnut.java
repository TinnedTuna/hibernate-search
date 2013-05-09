package org.hibernate.search.test.integration.jtaspring.sessionfactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

//Hibernate
@Entity
@Table(name = "doughnut")
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="doughnut")
// Hibernate Search
@Indexed(index="Doughnut")
@Analyzer(impl = StandardAnalyzer.class)
public class Doughnut {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long doughnutId;
	
	@Column(length = 255,nullable=false)
	private String kind;

        private Doughnut() {
		// Private no-arg constructor for Hibernate;
	}

	public Doughnut(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the doughnutId
	 */
	public Long getDoughnutId() {
		return doughnutId;
	}

	/**
	 * @param doughnutId the doughnutId to set
	 */
	public void setDoughnutId(Long doughnutId) {
		this.doughnutId = doughnutId;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	
}
