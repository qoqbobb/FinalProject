package com.springboot.project.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.model.ReplyVO;
import com.springboot.project.repository.QnaRepository;
import com.springboot.project.repository.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyRepository replyRepository;

	@Override
	public void save(ReplyVO vo) {
		
		replyRepository.save(vo);
		
	}

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}
