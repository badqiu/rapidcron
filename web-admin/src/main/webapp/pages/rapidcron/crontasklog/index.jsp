<%@page import="com.github.rapidcron.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronTaskLog 列表</title>
	
	<script src="${ctx}/js/rest.js" ></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.paginator.page},${page.paginator.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>


<rapid:override name="content">
	<form id="queryForm" name="queryForm" method="get" action="${ctx}/rapidcron/crontasklog/index.do">
	
	<div class="panel panel-default">
	
		<div class="panel-heading">CronTaskLog 列表</div>
		<div class="panel-body">
			
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="clientId" class="control-label">客户端ID</label>
						<input name="clientId" id="clientId" value="${query.clientId}" placeholder=""  class="form-control input-from-control"   maxlength="19"  class="validate-integer "/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="execTimeBegin" class="control-label">开始执行时间</label>
						<input name="execTimeBegin" id="execTimeBegin" value="<fmt:formatDate value='${query.execTimeBegin}' pattern='yyyy-MM-dd'/>"  placeholder="开始时间"   class="form-control input-from-control"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"   />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="execTimeEnd" class="control-label">结束执行时间</label>
						<input name="execTimeEnd" id="execTimeEnd" value="<fmt:formatDate value='${query.execTimeEnd}' pattern='yyyy-MM-dd'/>" placeholder="结束时间" class="form-control input-from-control"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"    />
					</div>
				</div>
			</div>

			
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="cronExpr" class="control-label">cron表达式</label>
						<input name="cronExpr" id="cronExpr" value="${query.cronExpr}" placeholder=""  class="form-control input-from-control"   maxlength="200"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="tasklog" class="control-label">任务日志</label>
						<input name="tasklog" id="tasklog" value="${query.tasklog}" placeholder=""  class="form-control input-from-control"   maxlength="2147483647"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="exitCode" class="control-label">执行结束退出码</label>
						<input name="exitCode" id="exitCode" value="${query.exitCode}" placeholder=""  class="form-control input-from-control"   maxlength="10"  class="validate-integer max-value-2147483647"/>
					</div>
				</div>
			</div>

				
			<div style="margin-top:20px"  class="row text-left">
				<div class="col-sm-5">
					<a href="#" class="btn btn-primary btn-sm"  onclick="$(this).closest('form').action='${ctx}/rapidcron/crontasklog/index.do'; $(this).closest('form').submit();return false;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default table-responsive">
		
		<table class="table table-hover scrolltable sortable">
		  <thead>
			  <tr>
				<th style="width:1px;"> </th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
<!-- 				<th sortColumn="id" >ID</th> -->
<!-- 				<th sortColumn="client_id" >客户端ID</th> -->
				<th sortColumn="exec_time" >执行时间</th>
				<th sortColumn="exec_end_time" >执行结束时间</th>
				<th sortColumn="cron_expr" >cron表达式</th>
				<th sortColumn="tasklog" >任务日志</th>
				<th sortColumn="exit_code" >执行结束退出码</th>
				<th sortColumn="exec_duration" >执行时长(秒)</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="row" varStatus="status">
		  	  
			  <tr>
				<td>${page.paginator.startRow + status.index}</td>
				
<%-- 				<td><c:out value='${row.id}'/>&nbsp;</td> --%>
<%-- 				<td><c:out value='${row.clientId}'/>&nbsp;</td> --%>
				<td><fmt:formatDate value='${row.execTime}' pattern='yyyy-MM-dd HH:dd'/>&nbsp;</td>
				<td><fmt:formatDate value='${row.execEndTime}' pattern='yyyy-MM-dd HH:dd'/>&nbsp;</td>
				<td><c:out value='${row.cronExpr}'/>&nbsp;</td>
				<td><c:out value='${row.tasklog}'/>&nbsp;</td>
				<td><c:out value='${row.exitCode}'/>&nbsp;</td>
				<td><c:out value='${row.execDuration / 1000.0}'/>&nbsp;</td>
				
				<td>
					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/crontasklog/show.do?id=${row.id}"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 查看</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-xs" href="${ctx}/rapidcron/crontasklog/delete.do?id=${row.id}" onclick="doRestDelete(this,'你确认删除?');return false;"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除</a>
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

