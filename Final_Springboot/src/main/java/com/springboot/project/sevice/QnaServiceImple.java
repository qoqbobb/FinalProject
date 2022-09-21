package com.springboot.project.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.model.QnaVO;
import com.springboot.project.repository.QnaRepository;

@Service
public class QnaServiceImple implements QnaService{

	@Autowired
	private QnaRepository dao;

	@Override
	public List<QnaVO> findAll() {
		return (List<QnaVO>) dao.findAll();
	}

	@Override
	public QnaVO get(QnaVO qna) {
		QnaVO get = dao.findById(qna.getQno()).get();
		dao.save(get);
		return get;
	}


	
	
	
	
	
}
