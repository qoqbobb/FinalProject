package com.springboot.project.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.project.model.QnaVO;
import com.springboot.project.sevice.QnaService;

import lombok.extern.log4j.Log4j;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService service;

	@RequestMapping("/list")
	void findAll(Model model){
		System.out.println("리스트");
		
		model.addAttribute("list",service.findAll());
		System.out.println(model);
		//return service.findAll();
		
	}
	
	@RequestMapping("/get")
	public void  get(QnaVO qna, Model model) {
		model.addAttribute("get", service.get(qna));
		System.out.println("목록");
		
		
	}
	
	
	
	
	
	
	
	
}
