<%@page import="com.github.rapidcron.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<input type="hidden" id="id" name="id" value="${cronTaskLog.id}"/>

	<div class="form-group">
		<label for="clientId" class="col-md-4 control-label"><span class="required">*</span>客户端ID:</label>
		<div class="col-md-4">
			<input value="${cronTaskLog.clientId}" name="clientId" id="clientId" class="form-control" maxlength="19" placeholder="clientId" required='true' digits='true' min='0' />
			<span class="help-block"></span>
			<span class="error"><form:errors path="clientId"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="execTime" class="col-md-4 control-label"><span class="required">*</span>执行时间:</label>
		<div class="col-md-4">
			<input value='<fmt:formatDate value="${cronTaskLog.execTime}" pattern="yyyy-MM-dd"/>' class="form-control"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execTime" name="execTime"  maxlength="0" />
			<span class="help-block"></span>
			<span class="error"><form:errors path="execTime"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="execEndTime" class="col-md-4 control-label">执行结束时间:</label>
		<div class="col-md-4">
			<input value='<fmt:formatDate value="${cronTaskLog.execEndTime}" pattern="yyyy-MM-dd"/>' class="form-control"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="execEndTime" name="execEndTime"  maxlength="0" />
			<span class="help-block"></span>
			<span class="error"><form:errors path="execEndTime"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="cronExpr" class="col-md-4 control-label"><span class="required">*</span>cron表达式:</label>
		<div class="col-md-4">
			<input value="${cronTaskLog.cronExpr}" name="cronExpr" id="cronExpr" class="form-control" maxlength="200" placeholder="cronExpr" required='true' />
			<span class="help-block"></span>
			<span class="error"><form:errors path="cronExpr"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="tasklog" class="col-md-4 control-label">任务日志:</label>
		<div class="col-md-4">
			<input value="${cronTaskLog.tasklog}" name="tasklog" id="tasklog" class="form-control" maxlength="2147483647" placeholder="tasklog" />
			<span class="help-block"></span>
			<span class="error"><form:errors path="tasklog"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="exitCode" class="col-md-4 control-label">执行结束退出码:</label>
		<div class="col-md-4">
			<input value="${cronTaskLog.exitCode}" name="exitCode" id="exitCode" class="form-control" maxlength="10" placeholder="exitCode" digits='true' min='0' />
			<span class="help-block"></span>
			<span class="error"><form:errors path="exitCode"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="execDuration" class="col-md-4 control-label">执行时长:</label>
		<div class="col-md-4">
			<input value="${cronTaskLog.execDuration}" name="execDuration" id="execDuration" class="form-control" maxlength="19" placeholder="execDuration" digits='true' min='0' />
			<span class="help-block"></span>
			<span class="error"><form:errors path="execDuration"/></span>
		</div>
	 </div>
	 
		