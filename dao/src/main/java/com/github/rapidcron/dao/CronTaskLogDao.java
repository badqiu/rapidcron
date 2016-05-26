/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao;

import java.util.Date;

import com.github.rapid.common.util.page.Page;
import com.github.rapidcron.model.CronTaskLog;
import com.github.rapidcron.query.CronTaskLogQuery;

/**
 * tableName: cron_task_log
 * [CronTaskLog] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CronTaskLogDao {
	
	public void insert(CronTaskLog entity);
	
	public int update(CronTaskLog entity);

	public int deleteById(long id);
	
	public CronTaskLog getById(long id);

	public Page<CronTaskLog> findPage(CronTaskLogQuery query);

	public CronTaskLog getLastTaskLogByCmd(long clientId, String cmd);	
	
}
