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
	.online_true{
		color: green;
		font-weight: bold;
	}
	.online_false{
		color: gray;
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
		<div class="panel-body">
			
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="hostname" class="control-label">主机名</label>
						<input name="hostname" id="hostname" value="${query.hostname}" placeholder=""  class="form-control input-from-control"   maxlength="50"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="ip" class="control-label">IP</label>
						<input name="ip" id="ip" value="${query.ip}" placeholder=""  class="form-control input-from-control"   maxlength="100"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="remarks" class="control-label">备注</label>
						<input name="remarks" id="remarks" value="${query.remarks}" placeholder=""  class="form-control input-from-control"   maxlength="200"  class=""/>
					</div>
				</div>
			</div>

			
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label for="cronContent" class="control-label">cron内容</label>
						<input name="cronContent" id="cronContent" value="${query.cronContent}" placeholder=""  class="form-control input-from-control"   maxlength="4000"  class=""/>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="clientStatus" class="control-label">在线状态</label>
						<input name="clientStatus" id="clientStatus" value="${query.clientStatus}" placeholder=""  class="form-control input-from-control"   maxlength="30"  class=""/>
					</div>
				</div>
			</div>

			<div style="margin-top:20px"  class="row text-left">
				<div class="col-sm-5">
					<a href="#" class="btn btn-primary btn-sm"  onclick="$(this).closest('form').action='${ctx}/rapidcron/cronclient/index.do'; $(this).closest('form').submit();return false;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default table-responsive">
		
		<table class="table table-hover scrolltable sortable">
		  <thead>
			  <tr>
				<th style="width:1px;"> </th>
				<th sortColumn="remarks" >备注</th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="hostname" >主机名</th>
				<th sortColumn="ip" >IP</th>
				<th sortColumn="run_user" >运行用户</th>
				<th>机器ID</th>
<!-- 				<th sortColumn="cron_content" >cron内容</th> -->
				<th>在线状态</th>
				<th>cron</th>
<!-- 				<th sortColumn="operator" >操作人员</th> -->
<!-- 				<th sortColumn="create_time" >创建时间</th> -->
<!-- 				<th sortColumn="update_time" >最后更新时间</th> -->
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="row" varStatus="status">
		  	  
			  <tr>
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${row.remarks}'/>&nbsp;</td>
				<td><c:out value='${row.hostname}'/>&nbsp;</td>
				<td><c:out value='${row.ip}'/>&nbsp;</td>
				<td><c:out value='${row.runUser}'/>&nbsp;</td>
				<td><c:out value='${row.mid}'/>&nbsp;</td>
<%-- 				<td><c:out value='${row.cronContent}'/>&nbsp;</td> --%>
				<td class="online_${row.online}">${row.online}</td>
				<td title="<c:out value='${row.cronContent}'/>"></td>
<%-- 				<td><c:out value='${row.operator}'/>&nbsp;</td> --%>
<%-- 				<td><fmt:formatDate value='${row.createTime}' pattern='yyyy-MM-dd'/>&nbsp;</td> --%>
<%-- 				<td><fmt:formatDate value='${row.updateTime}' pattern='yyyy-MM-dd'/>&nbsp;</td> --%>
				
				<td>
<%-- 					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/cronclient/show.do?clientId=${row.clientId}"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 查看</a>&nbsp;&nbsp; --%>
					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/cronclient/edit.do?clientId=${row.clientId}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 配置cron</a>&nbsp;&nbsp;
					<a class="btn btn-primary btn-xs" href="${ctx}/rapidcron/cronclient/clientStatus.do?clientId=${row.clientId}"></span>任务执行状态</a>&nbsp;&nbsp;
					<a class="btn btn-danger btn-xs" href="${ctx}/rapidcron/cronclient/delete.do?clientId=${row.clientId}" onclick="doRestDelete(this,'你确认删除?');return false;"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除</a>
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

