package com.db.sys.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;

@Repository
public class SysLogDaoImpl implements SysLogDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize) {
		//1.获取session对象
		SqlSession session=sqlSessionFactory.openSession();
		//2.执行查询
		String statement="com.db.sys.dao.SysLogDao.findPageObjects";
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("username", username);
		paramMap.put("startIndex", startIndex);
		paramMap.put("pageSize", pageSize);
		List<SysLog> records=session.selectList(statement, paramMap);
		//3.释放资源
		session.close();
		return records;
	}

	@Override
	public int getRowCount(String username) {
		//1.获取sqlSession对象
		SqlSession session=
		sqlSessionFactory.openSession();
		//2.获取SysLogDao实现对象
		//SysLogDao dao=session.getMapper(SysLogDao.class);
		String statement="com.db.sys.dao.SysLogDao.getRowCount";
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("username", username);
		//3.执行查询操作
		//int rows=dao.getRowCount(username);
		int rows=session.selectOne(statement, paramMap);
		//4.释放资源
		session.close();
		return rows;
	}
	@Override
	public int deleteObjects(Integer... id) {	
		return 0;
	}

}
