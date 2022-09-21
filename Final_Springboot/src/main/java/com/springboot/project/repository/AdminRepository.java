package com.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	//findby까진규칙 -> Username부턴 문법
	//select * from admin where username = ?
	public Admin findByUsername(String username);
	

}
