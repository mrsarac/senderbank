package edu.sabanciuniv.it526.bank.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.sabanciuniv.it526.bank.domain.Customer;
import edu.sabanciuniv.it526.bank.services.CustomerService;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String username;
	private String password;

	private Customer customer;

	@EJB
	private CustomerService customerService;

	public String login() {

		customer = customerService.getUserPass(username, password);

		if (customer != null) {
			System.out.println("register sayfasına gidiyoruz");
			return "customer/mybankaccount?faces-redirect=true";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Wrong credentials!!! Try to: admin:admin"));
			return "/login";
		}

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
