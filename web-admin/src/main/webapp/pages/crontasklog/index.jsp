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
	<form id="queryForm" name="queryForm" method="get" >
	
	<div class="panel panel-default">
	
		<div class="panel-heading">CronTaskLog 列表</div>
		<div class="panel-body">
			
			<div class="row">
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">客户端ID</div>
						<input class="form-control input-from-control" placeholder="客户端ID" value="${query.clientId}" id="clientId" name="clientId" maxlength="19"  class="validate-integer "/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">开始执行时间</div>
						<input class="form-control input-from-control" placeholder="开始时间" value="<fmt:formatDate value='${query.execTimeBegin}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execTimeBegin" name="execTimeBegin"   />
						<div class="input-group-addon">结束执行时间</div>
						<input class="form-control input-from-control" placeholder="结束时间" value="<fmt:formatDate value='${query.execTimeEnd}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execTimeEnd" name="execTimeEnd"   />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">开始执行结束时间</div>
						<input class="form-control input-from-control" placeholder="开始时间" value="<fmt:formatDate value='${query.execEndTimeBegin}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execEndTimeBegin" name="execEndTimeBegin"   />
						<div class="input-group-addon">结束执行结束时间</div>
						<input class="form-control input-from-control" placeholder="结束时间" value="<fmt:formatDate value='${query.execEndTimeEnd}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execEndTimeEnd" name="execEndTimeEnd"   />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">cron表达式</div>
						<input class="form-control input-from-control" placeholder="cron表达式" value="${query.cronExpr}" id="cronExpr" name="cronExpr" maxlength="200"  class=""/>
					</div>
				</div>
			</div>

			
			<div class="row">
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">任务日志</div>
						<input class="form-control input-from-control" placeholder="任务日志" value="${query.tasklog}" id="tasklog" name="tasklog" maxlength="2147483647"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">执行结束退出码</div>
						<input class="form-control input-from-control" placeholder="执行结束退出码" value="${query.exitCode}" id="exitCode" name="exitCode" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<div class="input-group-addon">执行时长</div>
						<input class="form-control input-from-control" placeholder="执行时长" value="${query.execDuration}" id="execDuration" name="execDuration" maxlength="19"  class="validate-integer "/>
					</div>
				</div>
			</div>

				
			<div style="margin-top:20px"  class="row col-sm-12 text-left">
				<input type="submit" class="btn btn-primary btn-sm"  value="查询" onclick="getReferenceForm(this).action='${ctx}/crontasklog/index.do'"/>
				<input type="button" class="btn btn-primary btn-sm"  value="新增" onclick="window.location = '${ctx}/crontasklog/add.do'"/>
				<input type="button" class="btn btn-primary btn-sm"  value="上传文件" onclick="window.location = '${ctx}/pages/crontasklog/upload.jsp'"/>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		
		<table class="table table-striped table-bordered table-hover table-condensed  scrolltable sortable">
		  <thead>
			  <tr>
				<th style="width:1px;"> </th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="client_id" >客户端ID</th>
				<th sortColumn="exec_time" >执行时间</th>
				<th sortColumn="exec_end_time" >执行结束时间</th>
				<th sortColumn="cron_expr" >cron表达式</th>
				<th sortColumn="tasklog" >任务日志</th>
				<th sortColumn="exit_code" >执行结束退出码</th>
				<th sortColumn="exec_duration" >执行时长</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr>
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${item.clientId}'/>&nbsp;</td>
				<td><fmt:formatDate value='${item.execTime}' pattern='yyyy-MM-dd'/>&nbsp;</td>
				<td><fmt:formatDate value='${item.execEndTime}' pattern='yyyy-MM-dd'/>&nbsp;</td>
				<td><c:out value='${item.cronExpr}'/>&nbsp;</td>
				<td><c:out value='${item.tasklog}'/>&nbsp;</td>
				<td><c:out value='${item.exitCode}'/>&nbsp;</td>
				<td><c:out value='${item.execDuration}'/>&nbsp;</td>
				
				<td>
					<a class="btn btn-primary btn-xs" href="${ctx}/crontasklog/show.do?id=${item.id}">查看</a>&nbsp;&nbsp;
					<a class="btn btn-primary btn-xs" href="${ctx}/crontasklog/edit.do?id=${item.id}">修改</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-xs" href="${ctx}/crontasklog/delete.do?id=${item.id}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar paginator="${page.paginator}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
		
	</div>
	
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

