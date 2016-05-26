/**
 * author: badqiu
 * depend on JQuery
 */
var SimpleTable = function(formId,pageNumber,pageSize,sortColumns,pageNumberKey,pageSizeKey,sortColumnsKey) {
	this.form = formId;
	this.pageNumber = pageNumber;
	this.pageSize = pageSize;
	this.sortColumns = sortColumns;
	this.pageNumberKey = pageNumberKey || 'page';
	this.pageSizeKey = pageSizeKey || 'pageSize';
	this.sortColumnsKey = sortColumnsKey || 'sortColumns';
	
	//handle sort
	_this = this;
	$("#"+formId+" .table th[sortColumn]").click(function() {
		//handle click sort header
		var column = $(this).attr('sortColumn');
		if(SimpleTableUtils.getSortDirection(sortColumns,column) == 'asc') {
			_this.toggleSort("");
		}else if(SimpleTableUtils.getSortDirection(sortColumns,column) == 'desc') {
			_this.toggleSort(column + " asc");
		}else {
			_this.toggleSort(column + " desc");
		}
	}).mouseover(function() {
		$(this).toggleClass('tableHeaderSortHover',true);
	}).mouseout(function() {
		$(this).toggleClass('tableHeaderSortHover',false);
	});
	
	// add 'desc' or 'asc' class to sorted tableHeader
	var sortInfos = SimpleTableUtils.getSortInfos(sortColumns);
	for(var i = 0; i < sortInfos.length; i++) {
		var info = sortInfos[i];
		var selector = "#"+formId+' .table th[sortColumn="'+info.column+'"]';
		var order = info.order ? info.order : 'asc';
		$(selector).addClass("sort " + order.toLowerCase());
	}
	
};
SimpleTable.prototype = {
	doJump : function() {
		var pair = function(k,v) {return ' <input type="hidden" name="'+k+'" value="'+v+'" />'};
		
		var params = pair(this.pageNumberKey,this.pageNumber) + 
			pair(this.pageSizeKey,this.pageSize) + 
			pair(this.sortColumnsKey,this.sortColumns);
		
		//alert("pageNumber:"+this.pageNumber+" pageSize:"+this.pageSize+" sortColumns:"+this.sortColumns+" this.form:"+this.form + "\n" + params);
		
		$('#'+this.form).append(params);
		$('#'+this.form).submit();
	},
	togglePage : function(pageNumber) {
		this.pageNumber = pageNumber;
		this.doJump();
	},
	togglePageSize : function(pageSize) {
		this.pageSize = pageSize;
		this.doJump();
	},
	toggleSort : function(sortColumns) {
		this.sortColumns = sortColumns;
		this.doJump();
	}
};

// static methods
var SimpleTableUtils = {
	getSortInfos : function(sortColumns) {
		if(!sortColumns) return []; 
		var results = [];
		var sorts = sortColumns.split(",");
		for(var i = 0; i < sorts.length; i++) {
			var columnAndOrder = sorts[i].split(/\s+/);
			var column = columnAndOrder[0];
			var order = columnAndOrder.length > 1 ? columnAndOrder[1] : null;
			
			var sortInfo = new Object();
			sortInfo.column = $.trim(column);
			sortInfo.order = $.trim(order);
			
			results.push(sortInfo);
		}
		return results;
	},
	
	getSortDirection : function(defaultSortColumns,currentColumn) {
		var infos = SimpleTableUtils.getSortInfos(defaultSortColumns);
		for(var i = 0; i < infos.length; i++) {
			var info = infos[i];
			var order = info.order ? info.order : 'asc';
			if(info.column == currentColumn) {
				return order;
			}
		}
		return null;
	}
}