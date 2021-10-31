package Jasper.SideProject.SpringSecurity.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Jasper.SideProject.SpringSecurity.Dto.PersonTranrs;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestController {

	@PostMapping("hello")
	public String hello() {
		return "hello security";
	}
	
	
	@PostMapping("index")
	public String index() {
		return "hello security Index";
	}
	
	@PostMapping("filter")
	public PersonTranrs filterTest() {
		
		PersonTranrs ps = new PersonTranrs();
		ps.setAge("24");
		ps.setName("jasper");
		ps.setWeight("78");
		return ps;
	}
	
	
}
