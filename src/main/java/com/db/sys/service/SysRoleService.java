package com.db.sys.service;

import java.util.List;

import com.db.common.vo.CheckBox;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysRole;
import com.db.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	
	List<CheckBox> findObjects();
	
	/**
	 * 基于id查询角色以及对应的菜单信息
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
	/**
	 * 更新角色以及角色对应的菜单信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(SysRole entity,
			Integer[] menuIds);
	/**
	 * 保存角色以及角色对应的菜单信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int saveObject(SysRole entity,
			       Integer[] menuIds);
	
	int deleteObject(Integer id);
	
	PageObject<SysRole> findPageObjects(
			  String name,
			  Integer pageCurrent);
}
