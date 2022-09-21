package com.springboot.project.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.project.model.Admin;
import com.springboot.project.repository.AdminRepository;

// 시큐리티 설정에서 loginProcessingUrl(/login);
// login요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행(규칙?)
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;

	//시큐리티 session = Authentication = UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin adminEntity = adminRepository.findByUsername(username);
		if(adminEntity != null) {
			return new PrincipalDetails(adminEntity);
		}
		
		
		return null;
	}

}
