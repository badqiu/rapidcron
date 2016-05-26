<%@page import="com.github.rapidcron.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronTaskLog 信息</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">查看 CronTaskLog</h2>
	
	<form:form modelAttribute="crontasklog" cssClass="form-horizontal"  >
		
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>客户端ID:</b></div>	
				<div class="col-xs-4">
					<c:out value='${cronTaskLog.clientId}'/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>执行时间:</b></div>	
				<div class="col-xs-4">
					<fmt:formatDate value='${cronTaskLog.execTime}' pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>执行结束时间:</b></div>	
				<div class="col-xs-4">
					<fmt:formatDate value='${cronTaskLog.execEndTime}' pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>cron表达式:</b></div>	
				<div class="col-xs-4">
					<c:out value='${cronTaskLog.cronExpr}'/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>任务日志:</b></div>	
				<div class="col-xs-4">
					<c:out value='${cronTaskLog.tasklog}'/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>执行结束退出码:</b></div>	
				<div class="col-xs-4">
					<c:out value='${cronTaskLog.exitCode}'/>
				</div>
			</div>
			<div class="row col-xs-12">
				<div class="col-xs-4 text-right"><b>执行时长:</b></div>	
				<div class="col-xs-4">
					<c:out value='${cronTaskLog.execDuration}'/>
				</div>
			</div>
		
		<div class="text-center">
			<input type="button" class="btn btn-primary" value="返回列表" onclick="window.location='${ctx}/crontasklog/index.do'"/>&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="后退" onclick="history.back();"/>
		</div>

	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>