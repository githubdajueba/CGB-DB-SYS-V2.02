package com.db.sys.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 此dao基于sys_role_menus关系表进行操作
 * @author Administrator
 */
public interface SysRoleMenuDao {
	
	/**
	 * 保存角色和菜单的关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(@Param("roleId")Integer roleId,
			          @Param("menuIds")Integer[] menuIds);
	/**
	 * 基于角色id删除角色和菜单关系数据
	 * @param id
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
     /**
      * 基于菜单id删除角色和菜单的关系数据.
      * @param menuId
      * @return
      */
	int deleteObjectsByMenuId(Integer menuId);
}
