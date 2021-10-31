package Jasper.SideProject.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Jasper.SideProject.SpringSecurity.Entity.UserData;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, String>{

	public UserData findByAccount(String userName);
}
