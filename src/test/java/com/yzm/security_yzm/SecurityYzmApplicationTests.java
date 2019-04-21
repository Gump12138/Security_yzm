package com.yzm.security_yzm;

import com.yzm.security_yzm.dao.TbUserMapper;
import com.yzm.security_yzm.pojo.TbUser;
import com.yzm.security_yzm.pojo.TbUserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityYzmApplicationTests {

	@Autowired
	TbUserMapper tbUserMapper;

	@Test
	public void contextLoads() {
		//调用用户服务对象,查询用户密码
		TbUserExample example=new TbUserExample();
		TbUserExample.Criteria criteria =example.createCriteria();
		criteria.andUsernameEqualTo("sunwukong");
		List<TbUser> user=tbUserMapper.selectByExample(example);
		System.out.println(user.get(0).getPassword());

	}

}
