/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao;

import com.github.rapidcron.model.*;
import com.github.rapidcron.query.*;

import java.util.List;

import com.github.rapid.common.util.page.Page;

/**
 * tableName: cron_client
 * [CronClient] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CronClientDao {
	
	public void insert(CronClient entity);
	
	public int update(CronClient entity);

	public int deleteById(long clientId);
	
	public CronClient getById(long clientId);
	

	public Page<CronClient> findPage(CronClientQuery query);

	public CronClient getByHostname(String hostname);

	public CronClient getByIP(String ip);

	public int updateLastHearbeatTime(String ip);	
	
}
