package Jasper.SideProject.SpringSecurity.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "userdata")
@Entity
public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNT")
	private String account;	
	
	@Column(name = "BALANCE")
	private String balance;
	
	public UserData() {}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBalance() {
		return balance;
	}
 
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
