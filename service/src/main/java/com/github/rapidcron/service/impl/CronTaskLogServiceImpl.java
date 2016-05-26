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

import com.github.rapidcron.service.CronTaskLogService;
import com.github.rapid.common.util.holder.BeanValidatorHolder;
import com.github.rapid.common.util.page.Page;
import com.github.rapidcron.model.*;
import com.github.rapidcron.dao.*;
import com.github.rapidcron.query.*;

import java.util.Date;

/**
 * [CronTaskLog] 的Service接口实现
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cronTaskLogService")
@Transactional
public class CronTaskLogServiceImpl implements CronTaskLogService {

	protected static final Logger logger = LoggerFactory.getLogger(CronTaskLogServiceImpl.class);
	
	/*
	* 请删除无用的方法，本代码生成器的理念是: 1. 一次生成，后期手工修改代码 2. 删除代码比手写重复代码快捷，所以请删除无用代码
	*/
	
	private CronTaskLogDao cronTaskLogDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCronTaskLogDao(CronTaskLogDao dao) {
		this.cronTaskLogDao = dao;
	}
	
	/** 
	 * 创建CronTaskLog
	 **/
	public CronTaskLog create(CronTaskLog cronTaskLog) {
	    Assert.notNull(cronTaskLog,"'cronTaskLog' must be not null");
	    initDefaultValuesForCreate(cronTaskLog);
	    new CronTaskLogChecker().checkCreateCronTaskLog(cronTaskLog);
	    cronTaskLogDao.insert(cronTaskLog);
	    return cronTaskLog;
	}
	
	/** 
	 * 更新CronTaskLog
	 **/	
    public CronTaskLog update(CronTaskLog cronTaskLog) {
        Assert.notNull(cronTaskLog,"'cronTaskLog' must be not null");
        new CronTaskLogChecker().checkUpdateCronTaskLog(cronTaskLog);
        cronTaskLogDao.update(cronTaskLog);
        return cronTaskLog;
    }	
    
    /**
     *  join取回CronTaskLog的关联信息,如一对多，多对一等的关联信息
     */
    public CronTaskLog join(CronTaskLog cronTaskLog) {
    	return cronTaskLog;
    }
    
	/** 
	 * 删除CronTaskLog
	 **/
    public void removeById(long id) {
        cronTaskLogDao.deleteById(id);
    }
    
	/** 
	 * 根据ID得到CronTaskLog
	 **/    
    public CronTaskLog getById(long id) {
        return cronTaskLogDao.getById(id);
    }
    
	/** 
	 * 分页查询: CronTaskLog
	 **/      
	@Transactional(readOnly=true)
	public Page<CronTaskLog> findPage(CronTaskLogQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cronTaskLogDao.findPage(query);
	}
	
    
	/**
	 * 创建对象时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CronTaskLog cronTaskLog) {
    }
    
    /**
     * CronTaskLog的属性检查类,根据自己需要编写自定义检查
     **/
    public class CronTaskLogChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCronTaskLog(CronTaskLog cronTaskLog) {
            checkCronTaskLog(cronTaskLog);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCronTaskLog(CronTaskLog cronTaskLog) {
            checkCronTaskLog(cronTaskLog);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCronTaskLog(CronTaskLog cronTaskLog) {
        	// Bean Validator检查,属性检查失败将抛异常
        	BeanValidatorHolder.validateWithException(cronTaskLog);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public CronTaskLog getLastTaskLogByCmd(long clientId, String cmd) {
		return cronTaskLogDao.getLastTaskLogByCmd(clientId, cmd);
	}
}
