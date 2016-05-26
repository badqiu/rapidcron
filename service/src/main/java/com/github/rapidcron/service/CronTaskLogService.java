/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.service;

import com.github.rapid.common.util.page.Page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapidcron.model.*;
import com.github.rapidcron.dao.*;
import com.github.rapidcron.query.*;

import java.util.Date;

/**
 * [CronTaskLog] 的Service接口
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CronTaskLogService {

	/** 
	 * 创建CronTaskLog
	 **/
	public CronTaskLog create(CronTaskLog cronTaskLog);
	
	/** 
	 * 更新CronTaskLog
	 **/	
    public CronTaskLog update(CronTaskLog cronTaskLog);
    
    /**
     *  join取回CronTaskLog的关联信息,如一对多，多对一等的关联信息
     */
    public CronTaskLog join(CronTaskLog cronTaskLog);
    
	/** 
	 * 删除CronTaskLog
	 **/
    public void removeById(long id);
    
	/** 
	 * 根据ID得到CronTaskLog
	 **/    
    public CronTaskLog getById(long id);
    
	/** 
	 * 分页查询: CronTaskLog
	 **/      
	public Page<CronTaskLog> findPage(CronTaskLogQuery query);

	public CronTaskLog getLastTaskLogByCmd(long clientId, String cmd);
	
    
}
