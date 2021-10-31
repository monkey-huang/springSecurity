package Jasper.SideProject.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Jasper.SideProject.SpringSecurity.Entity.UserSecurity;

public interface UserSecurityRepo extends JpaRepository<UserSecurity, String>{
	
	public UserSecurity findByAccount(String userName);

}
