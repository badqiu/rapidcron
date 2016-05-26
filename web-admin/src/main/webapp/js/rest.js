/**
 * api for RESTful operation
 */

/**
 * use case: <a href="/user/12" onclick="doRestDelete(this,'confirm delete?');return false;">delete</a>
 */
function doRestDelete(anchor,confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var form = document.createElement("form");
		form.style.display = "none";
		anchor.parentNode.appendChild(form);
		form.method = "POST";
		form.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "delete");
		form.appendChild(m);
		form.submit();
	}
}

function doRestBatchDelete(action,checkboxName,form) {
	if (!hasOneChecked(checkboxName)) {
		alert("请选择你要删除的对象!");
		return;
	}
	if (confirm("你确认要删除?")) {
		form.action = action;
		form.method = 'POST';
		
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "delete");
		form.appendChild(m);
		
		form.submit();
	}
}

