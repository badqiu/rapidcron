/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.github.rapid.common.beanutils.BeanUtils;
import com.github.rapidcron.model.CronTaskLog;


/**
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CronTaskLogUtil {
	
	public static List<CronTaskLog> toCronTaskLogList(List<Map> rows) {
		List<CronTaskLog> result = new ArrayList<CronTaskLog>();
		for(Map row : rows) {
			if(MapUtils.isEmpty(row))
				continue;
			
			CronTaskLog item = new CronTaskLog();
			BeanUtils.copyProperties(item, row);
			result.add(item);
		}
		return result;
	}
	
}
