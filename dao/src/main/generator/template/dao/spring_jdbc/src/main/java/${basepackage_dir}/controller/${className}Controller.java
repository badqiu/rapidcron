<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   

package ${basepackage}.controller;

import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.duowan.common.exception.MessageException;
import com.duowan.common.web.scope.Flash;

<#include "/java_imports.include">

/**
 * [${table.tableAlias}] 的业务操作
 * 
<#include "/java_description.include">
 *
 */
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller {
	
	private ${className}Service ${classNameFirstLower}Service;
	
	private final String LIST_ACTION = "redirect:/${classNameLowerCase}/index.do";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void set${className}Service(${className}Service service) {
		this.${classNameFirstLower}Service = service;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
	}
	
	/** 列表 */
	@RequestMapping(value="/index")
	public String index(ModelMap model,${className}Query query,HttpServletRequest request) {
		Page<${className}> page = this.${classNameFirstLower}Service.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/${classNameLowerCase}/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/show")
	public String show(ModelMap model,<@generateRequestParamArguments table.pkColumns/>) throws Exception {
		${className} ${classNameFirstLower} = (${className})${classNameFirstLower}Service.getById(<@generatePassingParameters table.pkColumns/>);
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return "/${classNameLowerCase}/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/add")
	public String add(ModelMap model,${className} ${classNameFirstLower}) throws Exception {
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return "/${classNameLowerCase}/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/create")
	public String create(ModelMap model,${className} ${classNameFirstLower},BindingResult errors) throws Exception {
		try {
			${classNameFirstLower}Service.create(${classNameFirstLower});
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/${classNameLowerCase}/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/${classNameLowerCase}/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/edit")
	public String edit(ModelMap model,<@generateRequestParamArguments table.pkColumns/>) throws Exception {
		${className} ${classNameFirstLower} = (${className})${classNameFirstLower}Service.getById(<@generatePassingParameters table.pkColumns/>);
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return "/${classNameLowerCase}/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/update")
	public String update(ModelMap model,<@generateRequestParamArguments table.pkColumns/>,${className} ${classNameFirstLower},BindingResult errors) throws Exception {
		try {
			${classNameFirstLower}Service.update(${classNameFirstLower});
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/${classNameLowerCase}/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/${classNameLowerCase}/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 批量删除 */
	@RequestMapping(value="/delete")
	public String delete(ModelMap model,<@generateRequestParamArguments table.pkColumns/>) {
		${classNameFirstLower}Service.removeById(<@generatePassingParameters table.pkColumns/>);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

<#macro generateRequestParamArguments columns>
<#compress>
<#list table.pkColumns as column> @RequestParam("${column.columnNameFirstLower}") ${column.primitiveJavaType} ${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>
</#compress>
</#macro>
