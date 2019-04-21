package com.yzm.security_yzm.springSecurity;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;


@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, CustomWebAuthenticationDetails> {

	public CustomAuthenticationDetailsSource() {
	}

	@Override
	public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new CustomWebAuthenticationDetails(context);
	}
}
