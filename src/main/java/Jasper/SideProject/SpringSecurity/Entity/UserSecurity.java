package Jasper.SideProject.SpringSecurity.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "usersecurity")
@Entity
public class UserSecurity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

}
