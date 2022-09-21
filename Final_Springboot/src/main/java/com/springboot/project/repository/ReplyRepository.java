

package com.springboot.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.project.model.QnaVO;
import com.springboot.project.model.ReplyVO;


public interface ReplyRepository extends JpaRepository<ReplyVO, Long>{

	 @Query("SELECT r FROM ReplyVO r WHERE r.qno = ?1 AND r.rno > 0 ORDER BY r.rno ASC ")
	public List<ReplyVO> getReplyiesofQna(QnaVO qno);

}
