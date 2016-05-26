/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Component;

import com.github.rapid.common.jdbc.dao.support.BaseSpringJdbcDao;
import com.github.rapid.common.util.ObjectUtil;
import com.github.rapid.common.util.page.Page;
import com.github.rapidcron.dao.CronTaskLogDao;
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
@Component("cronTaskLogDao")
public class CronTaskLogDaoImpl extends BaseSpringJdbcDao implements CronTaskLogDao{

	protected static final Logger logger = LoggerFactory.getLogger(CronTaskLogDaoImpl.class);
	
	/*
	* 请删除无用的方法，本代码生成器的理念是: 1. 一次生成，后期手工修改代码 2. 删除代码比手写重复代码快捷，所以请删除无用代码
	*/
	
	private RowMapper<CronTaskLog> entityRowMapper = new BeanPropertyRowMapper<CronTaskLog>(getEntityClass());
	
	static final private String COLUMNS = "id,client_id,exec_time,exec_end_time,cron_expr,tasklog,exit_code,exec_duration";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cron_task_log";
	
	@Override
	public Class<CronTaskLog> getEntityClass() {
		return CronTaskLog.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	public RowMapper<CronTaskLog> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CronTaskLog entity) {
		String sql = "insert into cron_task_log " 
			 + " (id,client_id,exec_time,exec_end_time,cron_expr,tasklog,exit_code,exec_duration) " 
			 + " values "
			 + " (:id,:clientId,:execTime,:execEndTime,:cronExpr,:tasklog,:exitCode,:execDuration)";
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CronTaskLog entity) {
		String sql = "update cron_task_log set "
					+ " client_id=:clientId,exec_time=:execTime,exec_end_time=:execEndTime,cron_expr=:cronExpr,tasklog=:tasklog,exit_code=:exitCode,exec_duration=:execDuration "
					+ " where  id = :id ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(long id) {
		String sql = "delete from cron_task_log where  id = ? ";
		return  getJdbcTemplate().update(sql,  id);
	}

	public CronTaskLog getById(long id) {
		String sql = SELECT_FROM + " where  id = ? ";
		return (CronTaskLog)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),id));
	}
	

	public Page<CronTaskLog> findPage(CronTaskLogQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cron_task_log where 1=1 ");
		if(ObjectUtil.isNotEmpty(query.getId())) {
            sql.append(" and id = :id ");
        }
		if(ObjectUtil.isNotEmpty(query.getClientId())) {
            sql.append(" and client_id = :clientId ");
        }
		if(ObjectUtil.isNotEmpty(query.getExecTimeBegin())) {
		    sql.append(" and exec_time >= :execTimeBegin ");
		}
		if(ObjectUtil.isNotEmpty(query.getExecTimeEnd())) {
            sql.append(" and exec_time <= :execTimeEnd ");
        }
		if(ObjectUtil.isNotEmpty(query.getCronExpr())) {
            sql.append(" and cron_expr = :cronExpr ");
        }
		if(ObjectUtil.isNotEmpty(query.getTasklog())) {
            sql.append(" and tasklog = :tasklog ");
        }
		if(ObjectUtil.isNotEmpty(query.getExitCode())) {
            sql.append(" and exit_code = :exitCode ");
        }
		if(ObjectUtil.isNotEmpty(query.getExecDuration())) {
            sql.append(" and exec_duration = :execDuration ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public CronTaskLog getLastTaskLogByCmd(long clientId, String cmd) {
		String sql = SELECT_FROM + " where  client_id = ? and cron_expr = ? order by exec_time desc limit 1 ";
		return (CronTaskLog)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),clientId,cmd));
	}
}
