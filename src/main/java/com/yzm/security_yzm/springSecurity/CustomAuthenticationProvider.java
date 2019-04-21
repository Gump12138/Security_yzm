package com.yzm.security_yzm.springSecurity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken token=(UsernamePasswordAuthenticationToken)authentication;

		String password=(String) token.getCredentials();
		String username=(String) token.getPrincipal();
		System.out.println(username);
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();

		String preCheckCode=details.getCheckCode();
		System.out.println("前端的验证码："+details.getCheckCode());


		UserCode user=(UserCode)userDetailsServiceImpl.loadUserByUsername(username);
		if (user==null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		String redisCheckCode=user.getCheckCode();
		if (!preCheckCode.equals("")&&!preCheckCode.isEmpty()){
			if (!redisCheckCode.isEmpty()){
				if (preCheckCode.equals(redisCheckCode)){
					if (user.getPassword().equals(password)){
						return token;
					}else {
						throw new BadCredentialsException("密码不正确");
					}
				}else {
					throw new BadCredentialsException("验证码不正确");
				}
			}else {
				throw new DisabledException("redis中没有找到验证码");
			}
		}else {
			throw new AuthenticationCredentialsNotFoundException("登陆时没有输入验证码");
		}
	}




	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}



}
