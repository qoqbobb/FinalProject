package com.springboot.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "NOTICE_BOARD")
@Entity
public class NBoard {
	
	@Id
	@GeneratedValue
	@Column(name = "NBNO")
	private Long nbno;
	
	@Column(name = "NTITLE")
	private String ntitle;
	
	@Column(name = "NCONTENT")
	private String ncontent;
	
	@Column(name = "NWRITER")
	private String nwriter;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date nregdate;
	
	@Column(insertable = true, updatable = true, columnDefinition = "date default sysdate")
	private Date nupdatedate;

}
