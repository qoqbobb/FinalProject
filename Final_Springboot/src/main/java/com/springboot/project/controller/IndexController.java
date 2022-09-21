package com.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.project.model.Admin;
import com.springboot.project.repository.AdminRepository;

@Controller
public class IndexController {
	
	@Autowired
	private AdminRepository adminrepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@GetMapping({"","/"})
	public String index() {
		return "index"; 
	}
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	} 
	@PostMapping("/join")
	public String join(Admin admin) {
		System.out.println(admin);
		admin.setRole("ROLE_ADMIN");
		admin.setNickname("관리자");
		String rawPassword = admin.getPassword();
		String encPaswword = bcryptPasswordEncoder.encode(rawPassword);
		admin.setPassword(encPaswword);
		adminrepository.save(admin); 
		return "redirect:/loginForm";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}


}
