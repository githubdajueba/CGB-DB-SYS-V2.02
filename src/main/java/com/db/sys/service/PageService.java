package com.db.sys.service;
import com.db.common.vo.PageObject;
public interface PageService<T> {
	 PageObject<T> findPageObjects(
			 String username,Integer pageCurrent);
}
