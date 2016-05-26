<%@page import="${basepackage}.model.*" %>
<#include "/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<#list table.columns as column>
<#if column.htmlHidden>
	<input type="hidden" id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl classNameLower+"."+column.columnNameLower/>"/>
</#if>
</#list>

<#list table.columns as column>
	<#if !column.htmlHidden>	
	<tr>	
		<td class="tdLabel">
			<#if !column.nullable><span class="required">*</span></#if>${column.columnAlias}:
		</td>		
		<td>
	<#if column.isDateTimeColumn>
		<input value='<fmt:formatDate value="<@jspEl classNameLower+"."+column.columnNameLower/>" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="${column.columnNameLower}" name="${column.columnNameLower}"  maxlength="0" class="${column.validateString}" />
	<#else>
		<form:input path="${column.columnNameLower}" id="${column.columnNameLower}" cssClass="${column.validateString}" maxlength="${column.size}" />
	</#if>
		<font color='red'><form:errors path="${column.columnNameLower}"/></font>
		</td>
	</tr>	
	
	</#if>
</#list>		