package com.springboot.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.project.model.Criteria;
import com.springboot.project.model.NBoard;
import com.springboot.project.model.PageDTO;
import com.springboot.project.sevice.NBoardService;

//아래 기본 시큐리티 적용안되게하는구문 시큐리티 구현시 삭제 
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Controller
public class NBoardController {

	@Autowired
	private NBoardService nboardService;
	
	@RequestMapping("/board/list")
	public String nList(Model model, NBoard nboard, Criteria cri) {
		List<NBoard> nlist = nboardService.getList(nboard, cri);
		model.addAttribute("nlist", nlist);
		int total = nboardService.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		return "/nboard/nlist";
	}
	
	@RequestMapping("/board/register")
	public String registerNBoardView() {
		return "/nboard/nregister";
	}
	
	@RequestMapping("/board/registerNBoard")
	public String registerNBoard(NBoard nboard) {
		nboardService.registerNBoard(nboard);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/get")
	public String getNBoard(Model model, NBoard nboard) {
		model.addAttribute("nboard", nboardService.get(nboard));
		return "/nboard/nget";
	}
	
	@RequestMapping("/board/modify")
	public String updateNBoardView(Model model, NBoard nboard) {
		model.addAttribute("nboard", nboardService.get(nboard));
		return "/nboard/nmodify";
	}
	
	@RequestMapping("/board/updateNBoard")
	public String updateNBoard(NBoard nboard) {
		nboardService.updateNBoard(nboard);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/deleteNBoard")
	public String deleteNBoard(NBoard nboard) {
		nboardService.deleteNBoard(nboard);
		return "redirect:/board/list";
	}
}
