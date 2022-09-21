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
@Table(name = "authorities")
@EntityListeners(AuditingEntityListener.class)
public class Authorities {
	
	
	
	@Column(name = "USERID")
	private String USERID;
	
	@Id
	@Column(name = "AUTH")
	private String AUTH;
	
	
}
