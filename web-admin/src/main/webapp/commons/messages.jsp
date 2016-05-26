<%@ include file="/commons/taglibs.jsp" %>

<c:if test="${flash.success != null}">
	<div class="alert alert-success">
		${flash.success}<br/>
	</div>    
</c:if>

<c:if test="${flash.error != null}">
	<div class="alert alert-danger">
		${flash.error}<br/>
	</div> 
</c:if>

<c:if test="${flash.warning != null}">
	<div class="alert alert-warning">
		${flash.warning}<br/>
	</div> 
</c:if>

<c:if test="${flash.info != null}">
	<div class="alert alert-info">
		${flash.info}<br/>
	</div>
</c:if>
