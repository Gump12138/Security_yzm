package com.yzm.security_yzm.springSecurity;

import com.yzm.security_yzm.dao.TbUserMapper;
import com.yzm.security_yzm.pojo.TbUser;
import com.yzm.security_yzm.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//定义角色封装集合
		List<GrantedAuthority> authorities = new ArrayList<>();
		//添加角色
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		//调用用户服务对象,查询用户密码
		TbUserExample example=new TbUserExample();
		TbUserExample.Criteria criteria =example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> user=tbUserMapper.selectByExample(example);
		String checkcode=getCheckCode().toLowerCase();

		return new UserCode(username,user.get(0).getPassword(),checkcode, authorities);
	}

	private String getCheckCode() {
		String checkCode=redisTemplate.opsForValue().get("CHECKCODE_SERVER");
		System.out.println("从redis中取出的验证码："+checkCode);
		return checkCode;
	}


}
