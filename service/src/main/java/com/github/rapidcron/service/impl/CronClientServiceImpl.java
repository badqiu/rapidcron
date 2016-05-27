/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapidcron.service.CronClientService;
import com.github.rapid.common.util.holder.BeanValidatorHolder;
import com.github.rapid.common.util.page.Page;
import com.github.rapidcron.model.*;
import com.github.rapidcron.dao.*;
import com.github.rapidcron.query.*;

import java.util.Date;

/**
 * [CronClient] 的Service接口实现
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cronClientService")
@Transactional
public class CronClientServiceImpl implements CronClientService {

	protected static final Logger logger = LoggerFactory.getLogger(CronClientServiceImpl.class);
	
	/*
	* 请删除无用的方法，本代码生成器的理念是: 1. 一次生成，后期手工修改代码 2. 删除代码比手写重复代码快捷，所以请删除无用代码
	*/
	
	private CronClientDao cronClientDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCronClientDao(CronClientDao dao) {
		this.cronClientDao = dao;
	}
	
	/** 
	 * 创建CronClient
	 **/
	public CronClient create(CronClient cronClient) {
	    Assert.notNull(cronClient,"'cronClient' must be not null");
	    initDefaultValuesForCreate(cronClient);
	    new CronClientChecker().checkCreateCronClient(cronClient);
	    cronClientDao.insert(cronClient);
	    return cronClient;
	}
	
	/** 
	 * 更新CronClient
	 **/	
    public CronClient update(CronClient cronClient) {
        Assert.notNull(cronClient,"'cronClient' must be not null");
        new CronClientChecker().checkUpdateCronClient(cronClient);
        cronClientDao.update(cronClient);
        return cronClient;
    }	
    
    /**
     *  join取回CronClient的关联信息,如一对多，多对一等的关联信息
     */
    public CronClient join(CronClient cronClient) {
    	return cronClient;
    }
    
	/** 
	 * 删除CronClient
	 **/
    public void removeById(long clientId) {
        cronClientDao.deleteById(clientId);
    }
    
	/** 
	 * 根据ID得到CronClient
	 **/    
    public CronClient getById(long clientId) {
        return cronClientDao.getById(clientId);
    }
    
	/** 
	 * 分页查询: CronClient
	 **/      
	@Transactional(readOnly=true)
	public Page<CronClient> findPage(CronClientQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cronClientDao.findPage(query);
	}
	
    
	/**
	 * 创建对象时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CronClient cronClient) {
    }
    
    /**
     * CronClient的属性检查类,根据自己需要编写自定义检查
     **/
    public class CronClientChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCronClient(CronClient cronClient) {
            checkCronClient(cronClient);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCronClient(CronClient cronClient) {
        	Assert.hasText(cronClient.getMid(),"mid must be not empty");
        	Assert.hasText(cronClient.getIp(),"ip must be not empty");
        	Assert.hasText(cronClient.getHostname(),"hostname must be not empty");
        	
            checkCronClient(cronClient);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCronClient(CronClient cronClient) {
        	// Bean Validator检查,属性检查失败将抛异常
        	BeanValidatorHolder.validateWithException(cronClient);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public CronClient getByHostname(String hostname) {
		return cronClientDao.getByHostname(hostname);
	}

	@Override
	public CronClient getByIP(String ip) {
		return cronClientDao.getByIP(ip);
	}

	@Override
	public void updateLastHearbeatTime(String mid) {
		cronClientDao.updateLastHearbeatTime(mid);
	}

	@Override
	public CronClient getByMid(String mid) {
		return cronClientDao.getByMid(mid);
	}
}
