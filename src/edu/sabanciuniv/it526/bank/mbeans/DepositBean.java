package edu.sabanciuniv.it526.bank.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import edu.sabanciuniv.it526.bank.domain.Account;
import edu.sabanciuniv.it526.bank.services.DepositeService;

@ManagedBean
@ViewScoped
public class DepositBean {

	private String iban;
	private int amount;
	private int newAmonut;
	private int sendAmonut;
	private String sendIban;

	public String getSendIban() {
		return sendIban;
	}

	public void setSendIban(String sendIban) {
		this.sendIban = sendIban;
	}

	@EJB
	private DepositeService depositeService;

	@PostConstruct
	private void init() {

		// int amt = Integer.parseInt(depositeService.gellAmount().get(0).toString());

		int amt = depositeService.getAccountByCid(1).getBalance();

		this.amount = amt;

	}

	public String depositIt() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		LoginBean myloginbean = (LoginBean) session.getAttribute("loginBean");

		int custId = myloginbean.getCustomer().getId();

		depositeService.setAmount(custId, newAmonut);
		
		return "mybankaccount";
	}
	
	
	public String sendIt() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		LoginBean myloginbean = (LoginBean) session.getAttribute("loginBean");

		int custId = myloginbean.getCustomer().getId();

		depositeService.sendAmount(custId, sendAmonut, sendIban);
		
		return "mybankaccount";
	}	

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNewAmonut() {
		return newAmonut;
	}

	public void setNewAmonut(int newAmonut) {
		this.newAmonut = newAmonut;
	}

	public int getSendAmonut() {
		return sendAmonut;
	}

	public void setSendAmonut(int sendAmonut) {
		this.sendAmonut = sendAmonut;
	}

}
