package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import com.db.common.vo.Node;
import com.db.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 将内存中的SysMenu对象持久化到数据库
	 * @param entity
	 * @return 更新记录的行数
	 */
	int updateObject(SysMenu entity);
	/**
	 * 将内存中的SysMenu对象持久化到数据库
	 * @param entity
	 * @return 写入记录的行数
	 */
	int insertObject(SysMenu entity);
	
    /**
     * 查询菜单对应的树结构信息
     * @return
     */
    List<Node> findZtreeMenuNodes();
	/**
	 * 判定此菜单是否有子菜单
	 * @param id
	 * @return 子菜单的数量
	 */
	int getChildCount(Integer id);
	/**
	 * 执行删除操作
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 查询所有菜单信息以及上级菜单的菜单名称
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}
