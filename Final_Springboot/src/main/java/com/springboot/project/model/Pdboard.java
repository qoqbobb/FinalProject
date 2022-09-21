package com.springboot.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Table(name = "PD_REGIST")
@Entity
public class Pdboard {

	@Id	// PK 설정
	@GeneratedValue
	@Column(name = "PD_CODE")
	private Long pdcode;
	
	@Column(name = "PD_TITLE")
	private String title;
	
	@Column(updatable = false, name = "ADMIN")
	private String admin;

	@Column(name = "PD_CONTENT")
	private String content;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date regdate;
	
	@Column(name = "PRICE")
	private Long price;

}
