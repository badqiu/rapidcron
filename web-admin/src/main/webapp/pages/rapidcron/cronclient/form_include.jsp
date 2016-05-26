<%@page import="com.github.rapidcron.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<input name="clientId" id="clientId" type="hidden" value="${cronClient.clientId}"/>

	<div class="form-group">
		<label for="hostname" class="col-sm-2 control-label">主机名:</label>
		<div class="col-sm-10">
			${cronClient.hostname}
			<span class="help-block"></span>
			<span class="error"><form:errors path="hostname"/></span>
		</div>
	 </div>
	 
	<div class="form-group">
		<label for="ip" class="col-sm-2 control-label">IP:</label>
		<div class="col-sm-10">
			${cronClient.ip}
			<span class="help-block"></span>
			<span class="error"><form:errors path="ip"/></span>
		</div>
	 </div>

	<div class="form-group">
		<label for="runUser" class="col-sm-2 control-label">运行用户:</label>
		<div class="col-sm-10">
			${cronClient.runUser}
			<span class="help-block"></span>
			<span class="error"><form:errors path="runUser"/></span>
		</div>
	 </div>
	 	 
	<div class="form-group">
		<label for="remarks" class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-10">
			<input name="remarks" id="remarks" value="${cronClient.remarks}" placeholder="备注"  maxlength="200"  class="form-control" />
			<span class="help-block"></span>
			<span class="error"><form:errors path="remarks"/></span>
		</div>
	 </div>

	 
	<div class="form-group">
		<label for="cronContent" class="col-sm-2 control-label">cron内容:</label>
		<div class="col-sm-10">
			<textarea name="cronContent" id="cronContent"  rows="25" cols="130" maxlength="4000">${cronClient.cronContent}</textarea>
			<span class="help-block"></span>
			<span class="error"><form:errors path="cronContent"/></span>
		</div>
	 </div>
	 
	 
		