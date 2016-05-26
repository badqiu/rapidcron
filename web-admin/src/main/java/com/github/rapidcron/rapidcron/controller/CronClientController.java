/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */


package com.github.rapidcron.rapidcron.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.rapid.common.exception.MessageException;
import com.github.rapid.common.util.ValidationErrorsUtil;
import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.web.scope.Flash;
import com.github.rapidcron.common.util.CronUtil;
import com.github.rapidcron.common.util.Crontab;
import com.github.rapidcron.model.CronClient;
import com.github.rapidcron.model.CronTaskLog;
import com.github.rapidcron.query.CronClientQuery;
import com.github.rapidcron.rapidcron.model.CronClientStatus;
import com.github.rapidcron.service.CronClientService;
import com.github.rapidcron.service.CronTaskLogService;

/**
 * [CronClient] 的Controller
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/rapidcron/cronclient")
public class CronClientController {

	/*
	* 请删除无用的方法，本代码生成器的理念是: 1. 一次生成，后期手工修改代码 2. 删除代码比手写重复代码快捷，所以请删除无用代码
	*/
	
	private static Logger logger = LoggerFactory.getLogger(CronClientController.class);
	
	private CronTaskLogService cronTaskLogService;
	private CronClientService cronClientService;
	
	private final String LIST_ACTION = "redirect:/rapidcron/cronclient/index.do";
	
	private static String CREATED_SUCCESS = "创建成功";
	private static String UPDATE_SUCCESS = "更新成功";
	private static String DELETE_SUCCESS = "删除成功";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCronClientService(CronClientService service) {
		this.cronClientService = service;
	}
	
	public void setCronTaskLogService(CronTaskLogService cronTaskLogService) {
		this.cronTaskLogService = cronTaskLogService;
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
	@RequestMapping
	public String index(ModelMap model,CronClientQuery query,HttpServletRequest request) {
		Page<CronClient> page = this.cronClientService.findPage(query);
		
		model.addAttribute("page",page);
		model.addAttribute("query",query);
		return "/rapidcron/cronclient/index";
	}
	
	/** 显示 */
	@RequestMapping
	public String show(ModelMap model,long clientId) throws Exception {
		CronClient cronClient = (CronClient)cronClientService.getById(clientId);
		model.addAttribute("cronClient",cronClient);
		return "/rapidcron/cronclient/show";
	}

	/** 进入新增 */
	@RequestMapping
	public String add(ModelMap model,CronClient cronClient) throws Exception {
		model.addAttribute("cronClient",cronClient);
		return "/rapidcron/cronclient/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,CronClient cronClient,BindingResult errors) throws Exception {
		try {
			cronClientService.create(cronClient);
		}catch(ConstraintViolationException e) {
			ValidationErrorsUtil.convert(e, errors);
			return  "/rapidcron/cronclient/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/rapidcron/cronclient/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping
	public String edit(ModelMap model,long clientId) throws Exception {
		CronClient cronClient = (CronClient)cronClientService.getById(clientId);
		model.addAttribute("cronClient",cronClient);
		return "/rapidcron/cronclient/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(method=RequestMethod.POST)
	public String update(ModelMap model,long clientId,CronClient cronClient,BindingResult errors) throws Exception {
		CronClient fromDb = cronClientService.getById(cronClient.getClientId());
		try {
			fromDb.setRemarks(cronClient.getRemarks());
			fromDb.setCronContent(cronClient.getCronContent());
			cronClientService.update(fromDb);
		}catch(ConstraintViolationException e) {
			ValidationErrorsUtil.convert(e, errors);
			return  "/rapidcron/cronclient/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/rapidcron/cronclient/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 删除 */
	@RequestMapping
	public String delete(ModelMap model,long clientId) {
		cronClientService.removeById(clientId);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	@RequestMapping
	public String clientStatus(ModelMap model,long clientId) {
		CronClient client = cronClientService.getById(clientId);
		List<Crontab> crontabList = CronUtil.parseCronMultiLine(client.getCronContent());
		List<CronClientStatus> statusList = new ArrayList<CronClientStatus>();
		for(Crontab crontab : crontabList) {
			CronTaskLog cronTaskLog = cronTaskLogService.getLastTaskLogByCmd(clientId,crontab.getCmd());
			CronClientStatus item = new CronClientStatus(crontab,cronTaskLog);
			statusList.add(item);
		}
		model.put("statusList", statusList);
		return "/rapidcron/cronclient/clientStatus";
	}
	
	@RequestMapping
	public String execCron(ModelMap model,long clientId,String cronCmd) {
		CronClient client = cronClientService.getById(clientId);
		throw new RuntimeException("not yet impl");
//		return "/rapidcron/cronclient/execCron";
	}
	
	/**
	 * 生成HTML: <select></select> 标签，生成的标签配合 jsp:include标签一起使用
	 * 应用场景：表之前有外键关联，如主从表，用于生成主从select标签,用于form表单的输入
	 * 
	 * <jsp:include page="${ctx}/rapidcron/cronclient/htmlSelectTag.do?selectId=someForeignKeyId"/>
	 * @param selectName select标签的name
	 */
	@RequestMapping
	public String htmlSelectTag(String selectName,String selected,ModelMap model) throws Exception {
		CronClientQuery query = new CronClientQuery();
		query.setPageSize(Integer.MAX_VALUE);
		Page<CronClient> page = cronClientService.findPage(query);
		model.put("itemList", page.getItemList());
		model.put("selected", selected);
		model.put("selectName", StringUtils.defaultIfEmpty(selectName,"cronClientId"));
		return "/rapidcron/cronclient/htmlSelectTag";
	}

}

