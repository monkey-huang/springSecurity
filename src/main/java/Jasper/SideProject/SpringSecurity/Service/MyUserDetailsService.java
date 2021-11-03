package Jasper.SideProject.SpringSecurity.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Jasper.SideProject.SpringSecurity.Entity.SecurityUser;
import Jasper.SideProject.SpringSecurity.Entity.User;
import Jasper.SideProject.SpringSecurity.Entity.UserSecurity;
import Jasper.SideProject.SpringSecurity.Repository.UserSecurityRepo;

// 要對到你autoweird的名字，這樣才能注入成功我們客製化的UserDetailsService
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserSecurityRepo userSecurityRepo;	
	
	// 這userDetails可以自己去implements 使用者擁有的結構是甚麼
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 第三個參數設置權限
		UserSecurity userData = userSecurityRepo.findByAccount(username);
		if(userData == null) {
			throw new UsernameNotFoundException("使用者不存在");
		}
		/**
		 * 1. 查詢出使用者資料
		 * 2. 查出使用者的權限資料
		 * 3. 放入implement的securityUser
		 * 4. 回傳securityUser
		 */
        List<String> permissionValueList = Arrays.asList(userData.getRole().split(","));

		SecurityUser securityUser = new SecurityUser();
		User user = new User();
		user.setUsername(userData.getAccount());
		user.setPassword(userData.getPassword());
		securityUser.setCurrentUserInfo(user);
		securityUser.setPermissionValueList(permissionValueList);
		return securityUser;
	}

}
