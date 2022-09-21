package com.springboot.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.project.model.Users;
import com.springboot.project.repository.UsersRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/users")
@Log
public class UserController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping("/list")
	public void userList(Model model,
			@PageableDefault(size = 10, sort = "USERNO", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		Page<Users> ulist = usersRepository.findAll(pageable);
		if(field.equals("USERID")) {
			//ulist = usersRepository.findByENABLEDContaining(word, pageable);
			ulist = usersRepository.findByUSERIDContaining(word, pageable);
		}
		else if (field.equals("USERNICKNAME")) {
			ulist = usersRepository.findByUSERNICKNAMEContaining(word, pageable);
		}
		int pageNumber = ulist.getPageable().getPageNumber();
		int totalPages = ulist.getTotalPages();
		int pageBlock = 5;
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage",startBlockPage);
		model.addAttribute("endBlockPage",endBlockPage);
		model.addAttribute("ulist",ulist);
		
		System.out.println(pageNumber);
		System.out.println(totalPages);
		
	}

}