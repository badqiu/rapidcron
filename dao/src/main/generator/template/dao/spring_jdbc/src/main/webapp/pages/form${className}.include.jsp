<%@page import="${basepackage}.model.*" %>
<#include "/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<#list table.columns as column>
<#if column.htmlHidden>
	<s:hidden id="${column.columnNameLower}" name="${column.columnNameLower}" />
</#if>
</#list>

<!-- ONGL access static field: @package.class@field or @vs@field -->
<#list table.columns as column>
	<#if !column.htmlHidden>
	
	<s:textfield label="${column.columnAlias}" key="${column.columnNameLower}" value="${column.columnNameLower}" required="${(!column.nullable)?string}" />
	
	</#if>
</#list>