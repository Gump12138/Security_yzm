package com.yzm.security_yzm.springSecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

public class UserCode extends User {
	private String checkCode;

	public UserCode(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserCode(String username, String password, String checkCode, Collection<? extends GrantedAuthority> authorities){
		super(username, password, authorities);
		this.checkCode=checkCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Override
	public String toString() {
		return super.toString()+"; UserCode{" +
				"checkCode='" + checkCode + '\'' +
				'}';
	}
}

