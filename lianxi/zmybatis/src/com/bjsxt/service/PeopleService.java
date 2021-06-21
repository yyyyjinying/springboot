package com.bjsxt.service;

import java.io.IOException;
import java.util.List;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.pojo.People;

public interface PeopleService {
	/**
	 * 显示全部
	 * @return
	 */
	List<People> show()  throws IOException;

	/**
	 * 分页显示
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	PageInfo showPage(int pageSize, int pageNumber) throws IOException;
}
