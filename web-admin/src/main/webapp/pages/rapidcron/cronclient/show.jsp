<%@page import="com.github.rapidcron.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>CronClient 信息</title>
</rapid:override>

<rapid:override name="content">
	<h2 id="title" class="text-center">查看 CronClient</h2>
	
	<form:form modelAttribute="cronclient" cssClass="form-horizontal"  >
		
			<div class="form-group">
				<label class="col-sm-4 control-label">客户端ID:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.clientId}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">主机名:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.hostname}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">IP:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.ip}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">备注:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.remarks}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">运行用户:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.runUser}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">cron内容:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.cronContent}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">在线状态:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.clientStatus}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">操作人员:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<c:out value='${cronClient.operator}'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">创建时间:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<fmt:formatDate value='${cronClient.createTime}' pattern="yyyy-MM-dd"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">最后更新时间:</label>	
				<div class="col-sm-4">
					<div class="form-control">
						<fmt:formatDate value='${cronClient.updateTime}' pattern="yyyy-MM-dd"/>
					</div>
				</div>
			</div>
		
			<div class="form-group">
				<div class="text-center">
					<a class="btn btn-default" href="${ctx}/rapidcron/cronclient/index.do">返回列表</a>&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-default" value="后退" onclick="history.back();"/>
				</div>
			</div>

	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>