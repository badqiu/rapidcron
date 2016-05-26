<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="paginator" required="true" type="com.github.rapid.common.util.page.Paginator" description="Paginator" %>
<%@ attribute name="pageSizeSelectList" type="java.lang.Number[]" required="false"  %>
<%@ attribute name="isShowPageSizeList" type="java.lang.Boolean" required="false"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
	Integer[] defaultPageSizes = new Integer[]{ 10 , 50 , 100 };
	if(jspContext.getAttribute("pageSizeSelectList") == null) {
		jspContext.setAttribute("pageSizeSelectList",defaultPageSizes); 
	}
	
	if(jspContext.getAttribute("isShowPageSizeList") == null) {
		jspContext.setAttribute("isShowPageSizeList",true); 
	} 
%>




<div class="fixed-table-pagination" style="display: block;">
	
	<div class="pull-left pagination-detail">
		<span class="pagination-info">${paginator.startRow} - ${paginator.endRow} of ${paginator.totalItems}</span>
		<span class="page-list">
			<select onChange="simpleTable.togglePageSize(this.value)" class="form-control" style="width:70px;">
				<c:forEach var="item" items="${pageSizeSelectList}">
					<option value="${item}" ${paginator.pageSize == item ? 'selected' : '' }>${item}</option>
				</c:forEach> 
			</select>
		</span>
	</div>
	
	<div class="pull-right pagination">
	  <ul class="pagination ">
	  	
	  	<!-- firstPage -->
	  	<li class="${paginator.firstPage ? 'disabled' : ''}">
	      <a aria-label="firstPage" href="javascript:simpleTable.togglePage(1);">
	        <span aria-hidden="true">|&laquo;</span>
	      </a>
	    </li>
	    
	    <!-- prePage -->
	    <li class="${paginator.hasPrePage ? '' : 'disabled'}">
	      <a aria-label="Previous" href="javascript:simpleTable.togglePage(${paginator.prePage});">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    
	    <!-- page -->
	    <c:forEach var="item" items="${paginator.slider}">
	    	<li class="${item == paginator.page ? 'active' : ''}">
				<c:choose>
				<c:when test="${item == paginator.page}"><a href="#">${item}</a></c:when>
				<c:otherwise><a href="javascript:simpleTable.togglePage(${item});">${item}</a></c:otherwise>
				</c:choose>
	    	</li>
		</c:forEach>
		
		<!-- nextPage -->
	    <li class="${paginator.hasNextPage ? '' : 'disabled'}">
	      <a aria-label="Next" href="javascript:simpleTable.togglePage(${paginator.nextPage});">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    
	    <!-- lastPage -->
	    <li class="${paginator.lastPage ? 'disabled' : ''}">
	      <a aria-label="lastPage" href="javascript:simpleTable.togglePage(${paginator.totalPages});">
	        <span aria-hidden="true">&raquo;|</span>
	      </a>
	    </li>
		
	  </ul>
	<div>
	
	
</div>
