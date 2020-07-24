package com.db.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysUser;
import com.db.sys.vo.SysUserDeptVo;
public interface SysUserDao {
	/**
	 * 基于id查询用户对应的相关信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	int updateObject(SysUser entity);
	
	int insertObject(SysUser entity);
	
	/**
	 * 禁用或启用用户信息
	 * @param id
	 * @param valid 状态值
	 * @param modifiedUser 修改用户
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	int getRowCount(@Param("username")String username);
	List<SysUserDeptVo> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
}
