package com.springboot.project.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.model.Criteria;
import com.springboot.project.model.NBoard;
import com.springboot.project.repository.NBoardRepository;

@Service
public class NBoardServiceImpl implements NBoardService{

	@Autowired
	private NBoardRepository nboardRepository;
	
	@Override
	public List<NBoard> getList(NBoard nboard, Criteria cri) {
		return (List<NBoard>) nboardRepository.findAll();
	}

	@Override
	public NBoard get(NBoard nboard) {
		NBoard findNBoard = nboardRepository.findById(nboard.getNbno()).get();
		nboardRepository.save(findNBoard);
		return findNBoard;
	}

	@Override
	public void updateNBoard(NBoard nboard) {
		NBoard findNBoard = nboardRepository.findById(nboard.getNbno()).get();
		findNBoard.setNbno(nboard.getNbno());
		findNBoard.setNtitle(nboard.getNtitle());
		findNBoard.setNcontent(nboard.getNcontent());
		findNBoard.setNwriter(nboard.getNwriter());
		nboardRepository.save(findNBoard);
	}

	@Override
	public void registerNBoard(NBoard nboard) {
		nboardRepository.save(nboard);
	}

	@Override
	public void deleteNBoard(NBoard nboard) {
		nboardRepository.deleteById(nboard.getNbno());
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}
}
