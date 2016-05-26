<%@page import="com.github.rapidcron.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronClient 列表</title>
	
	<script src="${ctx}/js/rest.js" ></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<style type="text/css">
	.exec_result_true{
		color:green;
		font-weight: bold;
	}
	.exec_result_false{
		color:red;
		font-weight: bold;
	}	
	</style>
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.paginator.page},${page.paginator.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>


<rapid:override name="content">
	<form id="queryForm" name="queryForm" method="get" action="${ctx}/rapidcron/cronclient/index.do">
	
	<div class="panel panel-default">
	
		<div class="panel-heading">CronClient 列表</div>

	</div>
	
	<div class="panel panel-default table-responsive">
		
		<table class="table table-hover scrolltable sortable">
		  <thead>
			  <tr>
				<th style="width:1px;"> </th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th>cron</th>
				<th>cmd</th>
				<th>最后执行时间</th>
				<th>执行耗时(秒)</th>
				<th>最后执行结果</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		  	  <c:forEach items="${statusList}" var="row" varStatus="status">
		  	  
			  <tr>
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${row.crontab.cron}'/>&nbsp;</td>
				<td><c:out value='${row.crontab.cmd}'/>&nbsp;</td>
				<td><fmt:formatDate value="${row.cronTaskLog.execTime}" pattern="yy-MM-dd HH:mm"/></td>
				<td>${row.cronTaskLog.execDuration/1000}</td>
				<td class="exec_result_${row.cronTaskLog.execSuccess}">${row.cronTaskLog.exitCode}</td>
				<td>
					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/crontasklog/index.do?clientId=${row.cronTaskLog.clientId}"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 查看日志</a>&nbsp;&nbsp;
					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/cronclient/execCron.do?cron=${row.crontab.cron}"><span class="glyphicon glyphicon-play" aria-hidden="true"></span> #强制执行</a>&nbsp;&nbsp;
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar paginator="${page.paginator}">
		</simpletable:pageToolbar>
		
	</div>
	
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

