package com.yzm.security_yzm.springSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class springSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationDetailsSource authenticationDetailsSource;

	/**
	 * 配置授权信息
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//禁止跨域
		http.csrf().disable();

		//资源的访问权限
		http.authorizeRequests()
				.antMatchers("/signin","/signup","/checkCode").permitAll()//允许访问登陆，注册
				.antMatchers("/assets/**").permitAll()
				.anyRequest().authenticated();// 其他任何请求,登录后可以访问
		//form表单登陆
		http.formLogin().authenticationDetailsSource(authenticationDetailsSource).loginPage("/signin").successForwardUrl("/index");//配置登录url
		//登出，注销
		http.logout().permitAll().logoutSuccessUrl("/signin");//注销,成功退出url
		http.rememberMe().rememberMeParameter("remeber-me");//开启记住我功能,默认会记住14天
	}


	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	/**
	 * 配置认证规则
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
		auth.eraseCredentials(false);
	}

}
