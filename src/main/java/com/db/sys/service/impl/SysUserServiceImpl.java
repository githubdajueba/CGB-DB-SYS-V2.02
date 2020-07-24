package com.db.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.common.util.PageUtil;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysUserDao;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;
import com.db.sys.vo.SysUserDeptVo;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		//1.参数校验
		if(id==null||id<1)
		throw new IllegalArgumentException("参数值无效");
		//2.基于用户id获取用户以及对应的部门信息
		SysUserDeptVo user=sysUserDao.findObjectById(id);
		if(user==null)
		throw new ServiceException("记录可能已经不存在");
		//3.基于用户id获取用户对应的角色信息
		List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(id);
		//4.对如上两次查询结果进行封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}
	
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
		throw new IllegalArgumentException("必须为用户分配角色");
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		if(rows==0)
	    throw new ServiceException("记录可能已经不存在");
		//4.保存用户与角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		//5.返回结果
		return rows;
	}
	
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为用户分配角色");
		//...
		//2.对密码进行加密
		String salt=UUID.randomUUID().toString();
		SimpleHash sh=new SimpleHash(
				"MD5",//algorithmName加密算法
				entity.getPassword(),//source 被加密的对象
				salt, //salt 盐值
				1);//hashIterations 加密
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		//3.保存用户自身信息
		int rows=sysUserDao.insertObject(entity);
		//4.保存用户与角色关系数据
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		//5.返回结果
		return rows;
	}
	
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if(id==null||id<1)
	    throw new IllegalArgumentException("id值无效");
		if(valid!=1&&valid!=0)
		throw new IllegalArgumentException("状态值无效");
		int rows=sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.判定参数有效性
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值不正确");
		//2.查询总记录数
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录信息
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records=sysUserDao.findPageObjects(username,
				startIndex, 
				pageSize);
		//4.封装数据并返回
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}

}
