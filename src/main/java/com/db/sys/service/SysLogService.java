package com.db.sys.service;

import com.db.sys.entity.SysLog;
public interface SysLogService extends PageService<SysLog>{
	
	   
	   /**
	    * 基于id删除日志信息
	    * @param ids
	    * @return
	    */
	   int deleteObjects(Integer... ids);
	  

}







