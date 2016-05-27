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
 * [CronClient] 的Service接口
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CronClientService {

	/** 
	 * 创建CronClient
	 **/
	public CronClient create(CronClient cronClient);
	
	/** 
	 * 更新CronClient
	 **/	
    public CronClient update(CronClient cronClient);
    
    /**
     *  join取回CronClient的关联信息,如一对多，多对一等的关联信息
     */
    public CronClient join(CronClient cronClient);
    
	/** 
	 * 删除CronClient
	 **/
    public void removeById(long clientId);
    
	/** 
	 * 根据ID得到CronClient
	 **/    
    public CronClient getById(long clientId);
    
	/** 
	 * 分页查询: CronClient
	 **/      
	public Page<CronClient> findPage(CronClientQuery query);

	public CronClient getByHostname(String hostname);

	public CronClient getByIP(String ip);

	public void updateLastHearbeatTime(String mid);

	public CronClient getByMid(String mid);
    
}
