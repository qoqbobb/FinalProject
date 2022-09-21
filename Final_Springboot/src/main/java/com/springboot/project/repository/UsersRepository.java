package com.springboot.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.project.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Page<Users> findAll(Pageable pageable);
	
	//Page<Users> findByENABLEDContaining(String ENABLED,Pageable pageable);
	
	
	Page<Users> findByUSERIDContaining(String USERID,Pageable pageable);
	
	Page<Users> findByUSERNICKNAMEContaining(String USERNICKNAME,Pageable pageable);
	
}
