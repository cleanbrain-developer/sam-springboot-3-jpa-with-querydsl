package com.example;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class CustomerRepository {
	private final EntityManager em;
	private final JPAQueryFactory jpaQueryFactory;
	private final QCustomer qCustomer = new QCustomer("customer");

	public CustomerRepository(EntityManager em) {
		this.em = em;
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}

	@Transactional
	public void create(Customer customer) {
		this.em.persist(customer);
	}

	// @Transactional
	// public void create(Customer customer) {
	// 	this.jpaQueryFactory.insert(qCustomer)
	// 		.columns(qCustomer.firstName, qCustomer.lastName)
	// 		.values(customer.getFirstName(), customer.getLastName())
	// 		.execute();
	// }

	public List<Customer> getCustomerList() {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.fetch();
	}

	public Customer getCustomer(long id) {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.where(qCustomer.id.eq(id))
			.fetchOne();
	}

	public List<Customer> getCustomerListByLastName(String lastName) {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.where(qCustomer.lastName.likeIgnoreCase(lastName))
			.fetch();
	}
}