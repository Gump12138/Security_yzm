package com.yzm.security_yzm.springSecurity;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;




public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
	private String checkCode;

	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.checkCode = (String)request.getParameter("checkCode");
	}

	public String getCheckCode() {
		return checkCode;
	}

	@Override
	public String toString() {
		return super.toString() + "; CheckCode: " + this.getCheckCode();
	}
}
