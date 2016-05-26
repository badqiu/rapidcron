<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!-- 生成html select标签，应用场景：表之前有外键关联，如主从表，用于生成主从select标签,用于form表单的输入,需配合 jsp:include 标签使用 -->
<select name="${selectName}" id="select_${selectName}"  multiple="multiple" class="form-control input-from-control" >
	<c:forEach	var="item" items="${itemList}">
		<option <c:if test="${item..clientId == selected}">selected="selected"</c:if> value="${item.clientId}">${item.clientId}</option>
	</c:forEach>
</select>
<script>
	$("#select_${selectName}").multipleSelect({
	    filter: true,
	    single: true,
	    mutiple:true
	});
</script>