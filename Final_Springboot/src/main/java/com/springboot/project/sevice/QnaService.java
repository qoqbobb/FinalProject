package com.springboot.project.sevice;

import java.util.List;

import com.springboot.project.model.QnaVO;

public interface QnaService {
	
	List<QnaVO> findAll();//글목록
	
	QnaVO get(QnaVO qna);//글상세
	
	
	
	

}
