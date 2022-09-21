package com.springboot.project.sevice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.project.model.FileVO;
import com.springboot.project.model.Pdboard;

@Service("com.springboot.project.service.PdService")
public interface PdService {

	
	 List<Pdboard> getpdBoardList(Pdboard pdboard);

	    void insertpdBoard(Pdboard pdboard);

	    Pdboard getpdBoard(Pdboard pdboard);

	    void updatepdBoard(Pdboard pdboard);

	    void deletepdBoard(Pdboard pdboard);
	    
}

	


