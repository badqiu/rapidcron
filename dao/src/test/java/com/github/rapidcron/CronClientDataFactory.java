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
 * 用于生成CronClient相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CronClientDataFactory {
	
	public static CronClientQuery newCronClientQuery() {
		CronClientQuery query = new CronClientQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setClientId(new Long("1"));
	  	query.setHostname(new String("1"));
	  	query.setIp(new String("1"));
	  	query.setRemarks(new String("1"));
	  	query.setRunUser(new String("1"));
	  	query.setCronContent(new String("1"));
	  	query.setClientStatus(new String("1"));
	  	query.setOperator(new String("1"));
		query.setCreateTimeBegin(new java.util.Date(System.currentTimeMillis()));
		query.setCreateTimeEnd(new java.util.Date(System.currentTimeMillis()));
		query.setUpdateTimeBegin(new java.util.Date(System.currentTimeMillis()));
		query.setUpdateTimeEnd(new java.util.Date(System.currentTimeMillis()));
		return query;
	}
	
	public static CronClient newCronClient() {
		CronClient obj = new CronClient();
		
	  	obj.setClientId(new Long("1"));
	  	obj.setHostname(new String("1"));
	  	obj.setIp(new String("1"));
	  	obj.setRemarks(new String("1"));
	  	obj.setRunUser(new String("1"));
	  	obj.setCronContent(new String("1"));
	  	obj.setClientStatus(new String("1"));
	  	obj.setOperator(new String("1"));
	  	obj.setCreateTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setUpdateTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}