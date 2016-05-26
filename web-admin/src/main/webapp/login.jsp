<%@ page contentType="text/html;charset=UTF-8" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<title>登陆</title>
	<style type="text/css">            
	
	</style> 	
</head>

<body>
<%@ include file="/commons/messages.jsp" %>

<div class="container" style="padding-left:30px;">
  	<div class="row">
  		<div class="col-xs-12">
  			<div id="titleRow" class="row" style="margin-top:0px;margin-bottom:20px;">
  				<h2 id="title" class="text-center">
  				用户登录
  				</h2>
  			</div>
  			
  			<form class="form-horizontal" role="form" name="actionForm" action="/gc/doLogin.do" method="post" style="margin-bottom:20px;">
		  		<input type="hidden" name="redirectURL" value="${redirectURL==null ? "" : redirectURL }" >
		  		
				<div class="form-group">
					<label for="passport" class="col-md-4 control-label">用户名:</label>
					<div class="col-md-4">
						<input  name="passport" id="passport" class="form-control" maxlength="50" placeholder="手机号码或用户名" required="true" />
						<span class="help-block"></span>
						<span class="error"><form:errors path="passport"/></span>
					</div>
				 </div>
				 
			 	<div class="form-group">
					<label for="password" class="col-md-4 control-label">密码:</label>
					<div class="col-md-4">
						<input  name="password" id="password" type="password" class="form-control" maxlength="50" placeholder="" required="true" />
						<span class="help-block"></span>
						<span class="error"><form:errors path="password"/></span>
					</div>
				 </div>

	  			<div id="tableRow" class="row">
	  				<div class="col-xs-12 text-center">
	  					 <input type="submit" class="btn btn-success"  value="登&nbsp;录"/>
	  				</div>
	  			</div>
  			
  			</form>
  			

  			
  		</div>
  	</div>
</div>

<script>
	new Validation(document.forms[0],{onSubmit:true});
</script>

</body>
</html>
