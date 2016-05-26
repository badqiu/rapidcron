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
import com.github.rapidcron.model.CronClient;


/**
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CronClientUtil {
	
	public static List<CronClient> toCronClientList(List<Map> rows) {
		List<CronClient> result = new ArrayList<CronClient>();
		for(Map row : rows) {
			if(MapUtils.isEmpty(row))
				continue;
			
			CronClient item = new CronClient();
			BeanUtils.copyProperties(item, row);
			result.add(item);
		}
		return result;
	}
	
}
