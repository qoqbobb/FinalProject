package com.springboot.project.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.model.Pdboard;
import com.springboot.project.repository.PdRepository;

@Service
public class PdServiceImpl implements PdService {
	
	@Autowired
    private PdRepository boardRepository;

    /**
     * 글목록 조회
     * @param pdboard
     * @return
     */
    @Override
    public List<Pdboard> getpdBoardList(Pdboard pdboard) {
        return (List<Pdboard>) boardRepository.findAll();
    }

    /**
     * 글쓰기 처리
     * @param pdboard
     */
    @Override
    public void insertpdBoard(Pdboard pdboard) {
        boardRepository.save(pdboard);
    }
    
    /**
     * 상세글 조회
     * @param Pdboard
     * @return
     */
    @Override
    public Pdboard getpdBoard(Pdboard pdboard) {
    	Pdboard findpdBoard = boardRepository.findById(pdboard.getPdcode()).get();
    	boardRepository.save(findpdBoard);
    	return findpdBoard;
    }

    /**
     * 글 수정
     * @param pdboard
     */
    @Override
    public void updatepdBoard(Pdboard pdboard) {
    	Pdboard findBoard = boardRepository.findById(pdboard.getPdcode()).get();
        
        // 가져온 글에 수정한 내용을 세팅한다.
        findBoard.setTitle(pdboard.getTitle());
        findBoard.setAdmin(pdboard.getAdmin());
        findBoard.setContent(pdboard.getContent());
        findBoard.setPrice(pdboard.getPrice());

        // DB에 저장
        boardRepository.save(findBoard);
    }
    
    /**
     * 글 삭제 처리
     * @param pdboard
     */
    @Override
    public void deletepdBoard(Pdboard pdboard) {
        boardRepository.deleteById(pdboard.getPdcode());
    }

}
