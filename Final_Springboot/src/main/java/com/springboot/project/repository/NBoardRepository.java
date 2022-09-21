package com.springboot.project.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.project.model.Criteria;
import com.springboot.project.model.NBoard;

public interface NBoardRepository extends CrudRepository<NBoard, Long>, JpaRepository<NBoard, Long> {


	/*
	public List<NBoard> findNBoardByNtitle(String ntitle);
	
	public Collection<NBoard> findByNwriterContaining(String nwriter);
	
	public Collection<NBoard> findByNtitleContaining(String ntitle);
	
	public Collection<NBoard> findByNtitleContainingOrNwriterContaining(String ntitle, String nwriter);
	
	public Collection<NBoard> findByNtitleContainingAndNbnoGreaterThan(String keyword, Long num);
	
	public Collection<NBoard> findByNbnoGreaterThanOrderByNbnoDesc(Long nbno);
	
	public List<NBoard> findByNbnoGreaterThanOrderByNbnoDesc(Long nbno, Pageable paging);
	
	public Page<NBoard> findByNbnoGreaterThan(Long nbno, Pageable paging);
	
	@Query("SELECT b FROM NBoard b WHERE b.ntitle like %?1% and b.nbno > 0 ORDER BY b.nbno desc")
	public List<NBoard> findByNtitle(String ntitle);


	@Query("select nboard.nbno, nboard.ntitle, nboard.nwriter, nboard.nregdate "
	+ " from NBoard nboard where nboard.ntitle like %?1% and nboard.nbno > 0 order by nboard.nbno desc")
	public List<Object[]> findByNtitle2(String ntitle);


	@Query("select nboard from NBoard nboard where nboard.nbno > 0 order by nboard.nbno desc")
	public List<NBoard> findBypage(Pageable pageable);

	@Query("SELECT b from NBoard b WHERE b.ncontent like %:ncontent%  and b.nbno > 0 order by b.nbno desc")
	public List<NBoard> findByContent(@Param("ncontent") String ncontent);
	*/
}
