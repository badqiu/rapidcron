

	/**
	 * ${sql.remarks!}
	 * sql: 
	 * <pre>${StringHelper.removeCrlf(sql.executeSql)?trim}</pre>
	 */
	public <@generateResultClassName sql 'DO'/> ${sql.operation}(<@generateOperationArguments sql/>) throws DataAccessException {
		<#if sql.paramType != "object" && !isUseParamObject(sql)>
			<#if (sql.params?size > 1)>
		Map<String,Object> param = new HashMap<String,Object>();
				<#list sql.params as param>
		param.put("${param.paramName}",${param.paramName});
				</#list>
			</#if>		
		</#if>		
		<@generateOperationMethodBody sql/>
	}


<#macro generateOperationMethodBody sql>
	<#local ibatisNamespace = appName+"."+tableConfig.className+".">
	<#if sql.params?size == 0>
		<#local paramName = 'null'>
	<#elseif sql.paramType = 'object'>
		<#local paramName = tableConfig.table.classNameFirstLower>		
	<#elseif sql.params?size == 1>
		<#local paramName = sql.params?first.paramName>
	<#else>
		<#local paramName = "param">
	</#if>
	<#if sql.selectSql>
		<#if sql.paging>
			<#if isUseParamObject(sql)>
		return (<@generateResultClassName sql 'DO'/>)PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"${sql.sql}",${paramName});
			<#else>
		return (<@generateResultClassName sql 'DO'/>)PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"${sql.sql}",${paramName},pageNum,pageSize);
			</#if>
		<#elseif sql.multiplicity = 'one'>
			<#local resultClassName><@generateResultClassName sql 'DO'/></#local>
			<#if resultClassName=='short' || resultClassName=='byte' || resultClassName == 'int' || resultClassName == 'long' || resultClassName == 'float' || resultClassName == 'double' >
		Number returnObject = (Number)getSqlMapClientTemplate().queryForObject("${sql.sql}",${paramName});
		if(returnObject == null)
			return (${resultClassName})0; 
		else
			return returnObject.${resultClassName}Value();
			<#else>
		return (<@generateResultClassName sql 'DO'/>)getSqlMapClientTemplate().queryForObject("${sql.sql}",${paramName});
			</#if>
		<#else>
		return (<@generateResultClassName sql 'DO'/>)getSqlMapClientTemplate().queryForList("${sql.sql}",${paramName});
		</#if>
	</#if>
	<#if sql.deleteSql>
		return getSqlMapClientTemplate().delete("${sql.sql}", ${paramName});
	</#if>
	<#if sql.insertSql>
		<#if sql.paramType = 'object'>
		getSqlMapClientTemplate().insert("${sql.sql}", ${paramName});
		return ${paramName}.get${tableConfig.pkColumn.columnName}();
		<#else>
		return getSqlMapClientTemplate().insert("${sql.sql}", ${paramName});
		</#if>    
	</#if>
	<#if sql.updateSql>
		return getSqlMapClientTemplate().update("${sql.sql}", ${paramName});
	</#if>
</#macro>