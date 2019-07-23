package edu.sabanciuniv.it526.bank.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.sabanciuniv.it526.bank.domain.Account;

@Stateless
public class DepositeService {

	@PersistenceContext
	private EntityManager em;

	public List<Account> gellAmount() {
		return em.createQuery("select account from Account account", Account.class).getResultList();
	}

	public Account getAccountByCid(int cid) {
		return em.find(Account.class, cid);
	}

	public void setAmount(int custId, int newAmonut) {

		Account acc = em.find(Account.class, custId);
		acc.setBalance(acc.getBalance() + newAmonut);

	}

	public int ibanToCustId(String sendIban) {
		
		Account acc0 = getCustId(sendIban);
		
		int custid =  acc0.getId();
		
		Account acc1 = em.find(Account.class, custid);

		return acc1.getCustomer().getId();

	}

	public Account getCustId(String sendIban) {

		List<Account> rs = em.createQuery("select a from Account a where a.iban = :iban", Account.class)
				.setParameter("iban", sendIban).getResultList();

		if (rs.size() == 1) {
			return rs.get(0);
		} else {
			return null;
		}

	}

	public void sendAmount(int custId, int sendAmonut, String sendIban) {

		Account acc1 = em.find(Account.class, custId);
		acc1.setBalance(acc1.getBalance() - sendAmonut);

		Account acc2 = em.find(Account.class, 2);

		acc2.setBalance(acc2.getBalance() + sendAmonut);

	}

}
