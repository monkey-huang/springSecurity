package Jasper.SideProject.SpringSecurity.Service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// 要對到你autoweird的名字，這樣才能注入成功我們客製化的UserDetailsService
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	// 這userDetails可以自己去implements 使用者擁有的結構是甚麼
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 可以role, role1, role2
		List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
		return new User("jasper", new BCryptPasswordEncoder().encode("123"), auths);
	}

}
