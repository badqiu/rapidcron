<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.impl;

<#include "/java_imports.include">

import ${basepackage}.dao.${className}Dao;
import java.io.Serializable;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import static com.duowan.common.util.ObjectUtils.*;

/**
 * tableName: ${table.sqlName}
 * [${table.tableAlias}] 的Dao操作 
 *  
<#include "/java_description.include">
*/
public class ${className}DaoImpl extends BaseSpringJdbcDao implements ${className}Dao{
	
	RowMapper<${className}> entityRowMapper = new BeanPropertyRowMapper<${className}>(getEntityClass());
	
	static final private String COLUMNS = "<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>";
	static final private String SELECT_FROM = "select " + COLUMNS + " from ${table.sqlName}";
	
	@Override
	public Class<${className}> getEntityClass() {
		return ${className}.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		<#if (table.pkCount >= 1)>
		return "${table.pkColumn.columnNameLower}";
		<#else>
		return null;
		</#if>
	}
	
	public RowMapper<${className}> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(${className} entity) {
		String sql = "insert into ${table.sqlName} " 
			 + " (<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>) " 
			 + " values "
			 + " (<#list table.columns as column>:${column.columnNameLower}<#if column_has_next>,</#if></#list>)";
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(${className} entity) {
		String sql = "update ${table.sqlName} set "
					+ " <#list table.notPkColumns as column>${column.sqlName}=:${column.columnNameLower}<#if column_has_next>,</#if></#list> "
					+ " where <#list table.pkColumns as column> ${column.sqlName} = :${column.columnNameLower} <#if column_has_next>and</#if></#list>";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(<@generateArguments table.pkColumns/>) {
		String sql = "delete from ${table.sqlName} where <#list table.pkColumns as column> ${column.sqlName} = ? <#if column_has_next>and</#if></#list>";
		return  getSimpleJdbcTemplate().update(sql,  <@generatePassingParameters table.pkColumns/>);
	}

	public ${className} getById(<@generateArguments table.pkColumns/>) {
		String sql = SELECT_FROM + " where <#list table.pkColumns as column> ${column.sqlName} = ? <#if column_has_next>and</#if></#list>";
		return (${className})DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),<@generatePassingParameters table.pkColumns/>));
	}
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.primitiveJavaType} ${column.columnNameFirstLower}) {
		String sql =  SELECT_FROM + " where ${column.sqlName}=?";
		return (${className})DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(), ${column.columnNameFirstLower}));
	}	
	
	</#if>
	</#list>

	public Page<${className}> findPage(${className}Query query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from ${table.sqlName} where 1=1 ");
		<#list table.columns as column>
		<#if column.isDateTimeColumn>
		if(isNotEmpty(query.get${column.columnName}Begin())) {
		    sql.append(" and ${column.sqlName} >= :${column.columnNameLower}Begin ");
		}
		if(isNotEmpty(query.get${column.columnName}End())) {
            sql.append(" and ${column.sqlName} <= :${column.columnNameLower}End ");
        }
		<#else>
		if(isNotEmpty(query.get${column.columnName}())) {
            sql.append(" and ${column.sqlName} = :${column.columnNameLower} ");
        }
		</#if>
		</#list>
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}
}
