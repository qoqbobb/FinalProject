package com.springboot.project.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.model.QnaVO;
import com.springboot.project.model.ReplyVO;
import com.springboot.project.repository.ReplyRepository;
import com.springboot.project.sevice.ReplyService;import com.sun.management.VMOption.Origin;

import lombok.extern.java.Log;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
@RequestMapping("/reply")
@Log
public class ReplyController {
	
	
	private final ReplyRepository replyRepository;
	
	@Autowired
	public ReplyController(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	//댓글등록
	@Transactional
	@PostMapping("/{qno}")
	public ResponseEntity<List<ReplyVO>> addReply(@PathVariable("qno") Long qno, @RequestBody ReplyVO reply){
		
		log.info("addReply() called..."); 
		log.info("qno : " + qno);
		log.info("Reply " + reply);
		
		QnaVO qna = new QnaVO();
		qna.setQno(qno);
		reply.setQno(qna);
		replyRepository.save(reply);
		
		return new ResponseEntity<>(getReplyiesofQna(qna), HttpStatus.CREATED);
		
	}
	
	//댓글 목록
	@GetMapping("/{qno}")
	public ResponseEntity<List<ReplyVO>> getReplyiesofQna(@PathVariable("qno") Long qno){
		log.info("replies () called");
		QnaVO qna = new QnaVO();
		qna.setQno(qno);
		return new ResponseEntity<>(getReplyiesofQna(qna), HttpStatus.OK);
	}

	//댓글목록
	private List<ReplyVO> getReplyiesofQna(QnaVO qna) throws RuntimeException{
		log.info("getReplyiesofQna () called...");
		return replyRepository.getReplyiesofQna(qna);
	}
	
	
	//댓글삭제
	@Transactional
	@DeleteMapping("/{qno}/{rno}")
	public ResponseEntity<List<ReplyVO>> remove(@PathVariable("qno") Long qno,
												@PathVariable("rno") Long rno){
		log.info("remove reply :" + rno);
		replyRepository.deleteById(rno);
		
		QnaVO qna = new QnaVO();
		qna.setQno(qno);
		
		return new ResponseEntity<>(getReplyiesofQna(qna),HttpStatus.OK);
		
	}
	
	//댓글수정
	@Transactional
	@PutMapping("/{rno}")
	public ResponseEntity<List<ReplyVO>> modify(@PathVariable("qno") Long qno, @RequestBody ReplyVO reply){
		log.info("modify reply : " + reply);
		
		replyRepository.findById(reply.getRno()).ifPresent(origin -> {
			origin.setReply(reply.getReply());
			replyRepository.save(origin);
		});
		
		QnaVO qna = new QnaVO();
		qna.setQno(qno);
		
		return new ResponseEntity<>(getReplyiesofQna(qna),HttpStatus.CREATED);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

