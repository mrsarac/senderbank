package edu.sabanciuniv.it526.bank.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.sabanciuniv.it526.bank.domain.Customer;

@Stateless
public class CustomerService {

	@PersistenceContext
	private EntityManager entityManager;

	public void registerCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	public List<Customer> allCustomers() {
		return entityManager.createQuery("select c from Customer s", Customer.class).getResultList();
	}

	public Customer getUserPass(String username, String password) {
		// TODO Auto-generated method stub

		// List<Student> rs = entityManager.createQuery("select student from Student
		// student", Student.class).getResultList();

		List<Customer> rs = entityManager.createQuery(
				"select c from Customer c where c.username = :username and c.password= :password",
				Customer.class).setParameter("username", username).setParameter("password", password).getResultList();

		if (rs.size() == 1) {
			return rs.get(0);
		} else {
			return null;
		}

	}

}
