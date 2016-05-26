<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>

	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>

	<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"  media="screen"/>
	<link href="<c:url value="/css/bootstrap.theme.min.css"/>" rel="stylesheet"  media="screen"/>
	<script src="<c:url value="/js/jquery.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/jquery.cookie.js"/>" type="text/javascript">"></script>
	<script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/underscore.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>

	<!-- multiple-select -->
	<script src="${ctx}/js/multiple-select/jquery.multiple.select.js"></script>
	<link href="${ctx}/js/multiple-select/multiple-select.css" rel="stylesheet"  media="screen"/>

	<!-- fileinput -->
	<script src="<c:url value="/js/fileinput/fileinput.js"/>"></script>
	<script src="<c:url value="/js/fileinput/fileinput_locale_zh.js"/>"></script>
	<link href="<c:url value="/js/fileinput/fileinput.css"/>" rel="stylesheet"  media="screen"/>
	
	<!-- jquery.validate doc: http://jqueryvalidation.org/ -->
	<script src="<c:url value="/js/jquery.validate/jquery.validate.js"/>"></script>
	<script src="<c:url value="/js/jquery.validate/additional-methods.js"/>"></script>
	<script src="<c:url value="/js/jquery.validate/localization/messages_zh.js"/>"></script>
	
	<!-- app -->
	<script src="<c:url value="/js/rest.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/app.js"/>" type="text/javascript"></script>
	<link href="<c:url value="/styles/global.css"/>" type="text/css" rel="stylesheet">
	