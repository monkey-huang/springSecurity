package Jasper.SideProject.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@PostMapping("hello")
	public String hello() {
		return "hello security";
	}
}
