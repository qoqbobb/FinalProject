package com.springboot.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "Users")
@EntityListeners(AuditingEntityListener.class)
public class Users {
	
	
	@Id
	@GeneratedValue
	@Column(name = "USER_NO")
	private Long USERNO;
	
	@Column(name = "USER_ID")
	private String USERID;
	
	@Column(name = "USER_PW")
	private String USER_PW;
	
	@Column(name = "USER_NICKNAME")
	private String USERNICKNAME;
	
	@Column(name = "USER_PHONE")
	private String USER_PHONE;
	
	@Column(name = "USER_EMAIL")
	private String USER_EMAIL;
	
	@CreatedDate
	private Date USER_REGDATE;
	
	@Column(name = "USER_POST")
	private String USER_POST;
	
	@Column(name = "USER_ADDR1")
	private String USER_ADDR1;
	
	@Column(name = "USER_ADDR2")
	private String USER_ADDR2;
	
	@Column(name = "ENABLED")
	private String ENABLED;
	
	
	
	
	
}
