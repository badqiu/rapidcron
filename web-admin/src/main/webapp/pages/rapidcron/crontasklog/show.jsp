<%@page import="com.github.rapidcron.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronTaskLog 信息</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">查看 CronTaskLog</h2>
	
	<form:form modelAttribute="crontasklog" cssClass="form-horizontal"  >
		
			<div class="form-group">
				<label class="col-sm-4 control-label">ID:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.id}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">客户端ID:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.clientId}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">执行时间:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<fmt:formatDate value='${cronTaskLog.execTime}' pattern="yyyy-MM-dd"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">执行结束时间:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<fmt:formatDate value='${cronTaskLog.execEndTime}' pattern="yyyy-MM-dd"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">cron表达式:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.cronExpr}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">任务日志:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.tasklog}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">执行结束退出码:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.exitCode}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">执行时长:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronTaskLog.execDuration}'/>
					</div>
				</div>
			</div>
		
			<div class="form-group">
				<div class="text-center">
					<a class="btn btn-default" href="${ctx}/rapidcron/crontasklog/index.do">返回列表</a>&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-default" value="后退" onclick="history.back();"/>
				</div>
			</div>

	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>