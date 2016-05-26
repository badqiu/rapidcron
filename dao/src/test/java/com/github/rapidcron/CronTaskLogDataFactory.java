/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron;

import java.util.*;

import com.github.rapidcron.model.*;
import com.github.rapidcron.query.*;

/**
 * 用于生成CronTaskLog相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CronTaskLogDataFactory {
	
	public static CronTaskLogQuery newCronTaskLogQuery() {
		CronTaskLogQuery query = new CronTaskLogQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setClientId(new Long("1"));
		query.setExecTimeBegin(new java.util.Date(System.currentTimeMillis()));
		query.setExecTimeEnd(new java.util.Date(System.currentTimeMillis()));
	  	query.setCronExpr(new String("1"));
	  	query.setTasklog(new String("1"));
	  	query.setExitCode(new Integer("1"));
		return query;
	}
	
	public static CronTaskLog newCronTaskLog() {
		CronTaskLog obj = new CronTaskLog();
		
	  	obj.setClientId(new Long("1"));
	  	obj.setExecTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setCronExpr(new String("1"));
	  	obj.setTasklog(new String("1"));
	  	obj.setExitCode(new Integer("1"));
		return obj;
	}
}