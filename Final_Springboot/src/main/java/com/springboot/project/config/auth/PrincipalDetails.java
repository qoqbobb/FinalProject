package com.springboot.project.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.project.model.Admin;

//시큐리티가 /login 주소 요청이 오면  낚아채서 로그인을 진행시킴
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어줌(security contextHolder)
//오브젝트 => Authentication 타입 객체
//Authentication 안에 user 정보가 있어야 댐.
//user오브젝트 타입 => userDetails 타입 객체
//Security Session => Authenticaton => UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails{
	
	private Admin admin; //콤포지션
	
	public PrincipalDetails(Admin admin) {
		this.admin = admin;
	}
	
	//해당 admin의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority>collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return admin.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		return admin.getUsername();
	}
	public String getnickname() {
		return admin.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		//우리 사이트!! 1년동안 회원이 로그인을 안하면 !! 휴면 계정으로 하기로 함.
		
		return true;
	}

}
