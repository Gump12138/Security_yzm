package com.yzm.security_yzm.dao;

import com.yzm.security_yzm.pojo.TbUser;
import com.yzm.security_yzm.pojo.TbUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbUserMapper {
	int countByExample(TbUserExample example);

	int deleteByExample(TbUserExample example);

	int deleteByPrimaryKey(Long id);

	int insert(TbUser record);

	int insertSelective(TbUser record);

	List<TbUser> selectByExample(TbUserExample example);

	TbUser selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByPrimaryKeySelective(TbUser record);

	int updateByPrimaryKey(TbUser record);
}
