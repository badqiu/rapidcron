<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronTaskLog编辑</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">编辑CronTaskLog</h2>
	
	<form:form id="inputForm" method="put" cssClass="form-horizontal" action="${ctx}/crontasklog/update.do" modelAttribute="cronTaskLog">
			
		<%@ include file="form_include.jsp" %>
		
		<div class="form-group">
		    <div class="text-center">
				<input id="submitButton" class="btn btn-success" name="submitButton" type="submit" value="提交" />&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-primary" value="返回列表" onclick="window.location='${ctx}/crontasklog/index.do'"/>&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-primary" value="后退" onclick="history.back();"/>
		    </div>
		</div>
		
	</form:form>
	
	<script>
		$("#inputForm").validate();
	</script>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>