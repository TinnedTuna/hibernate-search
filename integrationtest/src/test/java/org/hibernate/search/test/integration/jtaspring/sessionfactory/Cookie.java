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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

//Hibernate
@Entity
@Table(name = "cookie")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "cookie")
// Hibernate Search
@Indexed(index = "Cookie")
@Analyzer(impl = StandardAnalyzer.class)
public class Cookie {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cookieId;

	@Column( length = 255, nullable = false )
	private String kind;

	private Cookie() {
		// Private no-arg constructor for Hibernate;
	}

	public Cookie(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the cookieId 
	 */
	public Long getCookieId() {
		return cookieId;
	}

	/**
	 * @param cookieId the cookieId to set
	 */
	public void setCookieId(Long cookieId) {
		this.cookieId = cookieId;
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
