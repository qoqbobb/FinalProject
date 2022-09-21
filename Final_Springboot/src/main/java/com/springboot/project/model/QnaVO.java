package com.springboot.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;


@Data
@Entity
@Table(name = "qna_board")
@EntityListeners(AuditingEntityListener.class)
public class QnaVO {
	
	
	@Id
	@GeneratedValue
	@Column(name = "qno")
	private Long qno;
	
	@Column(name = "q_title")
	private String q_title;
	
	@Column(name = "q_content")
	private String q_content;
	
	@Column(name = "q_writer")
	private String q_writer;
	
	@CreatedDate
	private Date regdate;
	
	@CreatedDate
	private Date updatedate;
	
//	@OneToMany(mappedBy = "qno", fetch = FetchType.LAZY)
//	private List<ReplyVO> replies;
	
	
}
