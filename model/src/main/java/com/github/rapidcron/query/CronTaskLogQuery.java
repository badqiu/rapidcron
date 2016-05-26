/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import com.github.rapid.common.util.page.PageQuery;

/**
 * [CronTaskLog] 查询对象
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CronTaskLogQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** ID */
	private Long id;
	/** 客户端ID */
	private Long clientId;
	/** 执行时间 */
	private java.util.Date execTimeBegin;
	private java.util.Date execTimeEnd;
	/** 执行结束时间 */
	private java.util.Date execEndTime;
	/** cron表达式 */
	private String cronExpr;
	/** 任务日志 */
	private String tasklog;
	/** 执行结束退出码 */
	private Integer exitCode;
	/** 执行时长 */
	private Long execDuration;

	public Long getId() {
		return this.id;
	}
	
	public CronTaskLogQuery setId(Long id) {
		this.id = id;
		return this;
	}
	
	public Long id() {
		return this.id;
	}

	public CronTaskLogQuery id(Long id) {
		this.id = id;
		return this;
	}
	
	public Long getClientId() {
		return this.clientId;
	}
	
	public CronTaskLogQuery setClientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public Long clientId() {
		return this.clientId;
	}

	public CronTaskLogQuery clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public java.util.Date getExecTimeBegin() {
		return this.execTimeBegin;
	}
	
	public CronTaskLogQuery setExecTimeBegin(java.util.Date value) {
		this.execTimeBegin = value;
		return this;
	}	
	
	public java.util.Date getExecTimeEnd() {
		return this.execTimeEnd;
	}
	
	public CronTaskLogQuery setExecTimeEnd(java.util.Date value) {
		this.execTimeEnd = value;
		return this;
	}
	
	public java.util.Date getExecEndTime() {
		return this.execEndTime;
	}
	
	public CronTaskLogQuery setExecEndTime(java.util.Date execEndTime) {
		this.execEndTime = execEndTime;
		return this;
	}
	
	public java.util.Date execEndTime() {
		return this.execEndTime;
	}

	public CronTaskLogQuery execEndTime(java.util.Date execEndTime) {
		this.execEndTime = execEndTime;
		return this;
	}
	
	public String getCronExpr() {
		return this.cronExpr;
	}
	
	public CronTaskLogQuery setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
		return this;
	}
	
	public String cronExpr() {
		return this.cronExpr;
	}

	public CronTaskLogQuery cronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
		return this;
	}
	
	public String getTasklog() {
		return this.tasklog;
	}
	
	public CronTaskLogQuery setTasklog(String tasklog) {
		this.tasklog = tasklog;
		return this;
	}
	
	public String tasklog() {
		return this.tasklog;
	}

	public CronTaskLogQuery tasklog(String tasklog) {
		this.tasklog = tasklog;
		return this;
	}
	
	public Integer getExitCode() {
		return this.exitCode;
	}
	
	public CronTaskLogQuery setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
		return this;
	}
	
	public Integer exitCode() {
		return this.exitCode;
	}

	public CronTaskLogQuery exitCode(Integer exitCode) {
		this.exitCode = exitCode;
		return this;
	}
	
	public Long getExecDuration() {
		return this.execDuration;
	}
	
	public CronTaskLogQuery setExecDuration(Long execDuration) {
		this.execDuration = execDuration;
		return this;
	}
	
	public Long execDuration() {
		return this.execDuration;
	}

	public CronTaskLogQuery execDuration(Long execDuration) {
		this.execDuration = execDuration;
		return this;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

