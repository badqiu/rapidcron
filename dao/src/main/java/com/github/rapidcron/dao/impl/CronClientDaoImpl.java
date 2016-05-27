/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao.impl;

import com.github.rapidcron.model.*;
import com.github.rapidcron.query.*;
import com.github.rapidcron.dao.CronClientDao;







import java.io.Serializable;
import java.util.List;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.util.ObjectUtil;
import com.github.rapid.common.jdbc.dao.support.BaseSpringJdbcDao;

/**
 * tableName: cron_client
 * [CronClient] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
@Component("cronClientDao")
public class CronClientDaoImpl extends BaseSpringJdbcDao implements CronClientDao{

	protected static final Logger logger = LoggerFactory.getLogger(CronClientDaoImpl.class);
	
	/*
	* 请删除无用的方法，本代码生成器的理念是: 1. 一次生成，后期手工修改代码 2. 删除代码比手写重复代码快捷，所以请删除无用代码
	*/
	
	private RowMapper<CronClient> entityRowMapper = new BeanPropertyRowMapper<CronClient>(getEntityClass());
	
	static final private String COLUMNS = "client_id,hostname,ip,remarks,run_user,cron_content,client_status,operator,create_time,update_time,last_hearbeat_time,mid";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cron_client";
	
	@Override
	public Class<CronClient> getEntityClass() {
		return CronClient.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "clientId";
	}
	
	public RowMapper<CronClient> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CronClient entity) {
		String sql = "insert into cron_client " 
			 + " (client_id,hostname,ip,remarks,run_user,cron_content,client_status,operator,create_time,update_time,last_hearbeat_time,mid) " 
			 + " values "
			 + " (:clientId,:hostname,:ip,:remarks,:runUser,:cronContent,:clientStatus,:operator,:createTime,:updateTime,:lastHearbeatTime,:mid)";
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CronClient entity) {
		String sql = "update cron_client set "
					+ " hostname=:hostname,ip=:ip,remarks=:remarks,run_user=:runUser,cron_content=:cronContent,client_status=:clientStatus,operator=:operator,create_time=:createTime,update_time=:updateTime,last_hearbeat_time=:lastHearbeatTime,mid=:mid "
					+ " where  client_id = :clientId ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public int updateLastHearbeatTime(String mid) {
		String sql = "update cron_client set "
				+ " last_hearbeat_time=? "
				+ " where  mid = ? ";
		return getJdbcTemplate().update(sql,new Date(),mid);
	}
	
	public int deleteById(long clientId) {
		String sql = "delete from cron_client where  client_id = ? ";
		return  getJdbcTemplate().update(sql,  clientId);
	}

	public CronClient getById(long clientId) {
		String sql = SELECT_FROM + " where  client_id = ? ";
		return (CronClient)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),clientId));
	}
	

	public Page<CronClient> findPage(CronClientQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cron_client where 1=1 ");
		if(ObjectUtil.isNotEmpty(query.getClientId())) {
            sql.append(" and client_id = :clientId ");
        }
		if(ObjectUtil.isNotEmpty(query.getHostname())) {
            sql.append(" and hostname = :hostname ");
        }
		if(ObjectUtil.isNotEmpty(query.getIp())) {
            sql.append(" and ip = :ip ");
        }
		if(ObjectUtil.isNotEmpty(query.getRemarks())) {
            sql.append(" and remarks = :remarks ");
        }
		if(ObjectUtil.isNotEmpty(query.getRunUser())) {
            sql.append(" and run_user = :runUser ");
        }
		if(ObjectUtil.isNotEmpty(query.getCronContent())) {
            sql.append(" and cron_content = :cronContent ");
        }
		if(ObjectUtil.isNotEmpty(query.getClientStatus())) {
            sql.append(" and client_status = :clientStatus ");
        }
		if(ObjectUtil.isNotEmpty(query.getOperator())) {
            sql.append(" and operator = :operator ");
        }
		if(ObjectUtil.isNotEmpty(query.getCreateTimeBegin())) {
		    sql.append(" and create_time >= :createTimeBegin ");
		}
		if(ObjectUtil.isNotEmpty(query.getCreateTimeEnd())) {
            sql.append(" and create_time <= :createTimeEnd ");
        }
		if(ObjectUtil.isNotEmpty(query.getUpdateTimeBegin())) {
		    sql.append(" and update_time >= :updateTimeBegin ");
		}
		if(ObjectUtil.isNotEmpty(query.getUpdateTimeEnd())) {
            sql.append(" and update_time <= :updateTimeEnd ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public CronClient getByHostname(String hostname) {
		String sql = SELECT_FROM + " where  hostname = ? ";
		return (CronClient)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),hostname));
	}

	@Override
	public CronClient getByIP(String ip) {
		String sql = SELECT_FROM + " where  ip = ? ";
		return (CronClient)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),ip));
	}

	@Override
	public CronClient getByMid(String mid) {
		String sql = SELECT_FROM + " where  mid = ? ";
		return (CronClient)DataAccessUtils.singleResult(getJdbcTemplate().query(sql, getEntityRowMapper(),mid));
	}


}
