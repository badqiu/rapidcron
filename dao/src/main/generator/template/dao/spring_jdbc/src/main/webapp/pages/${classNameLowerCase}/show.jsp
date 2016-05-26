<%@page import="${basepackage}.model.*" %>
<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = className?lower_case> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${table.tableAlias} 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="${classNameLowerCase}"  >
		<input type="button" value="返回列表" onclick="window.location='<@jspEl 'ctx'/>/${classNameLowerCase}/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
	<#list table.columns as column>
	<#if column.pk>
		<input type="hidden" id="${column.columnNameLower}" name="${column.columnNameLower}" value="<@jspEl classNameLower+"."+column.columnNameLower/>"/>
	</#if>
	</#list>
	
		<table class="formTable">
		<#list table.columns as column>
		<#if !column.htmlHidden>
			<tr>	
				<td class="tdLabel">${column.columnAlias}</td>	
				<td><#rt>
				<#compress>
				<#if column.isDateTimeColumn>
				<fmt:formatDate value='<@jspEl classNameLower+"."+column.columnNameLower/>' pattern="yyyy-MM-dd"/>
				<#else>
				<c:out value='<@jspEl classNameLower+"."+column.columnNameLower/>'/>
				</#if>
				</#compress>
				<#lt></td>
			</tr>
		</#if>
		</#list>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>