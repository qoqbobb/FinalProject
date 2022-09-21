package com.springboot.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "pd_image")
@Entity
public class FileVO {
	
	@Id	// PK 설정
	@GeneratedValue
	@Column(name = "fno")
	private int fno;
	
	@Column(name = "pd_code")
	private Long pd_code;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "fileOriName")
	private String fileOriName;
	
	@Column(name = "uploadPath")
	private String uploadPath;
	
}
