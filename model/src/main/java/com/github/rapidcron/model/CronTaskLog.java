/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.model;

import javax.validation.constraints.*;
import java.util.*;
import org.hibernate.validator.constraints.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



/**
 * tableName: cron_task_log [CronTaskLog] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CronTaskLog  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_EXEC_TIME = "yyyy-MM-dd";
	public static final String FORMAT_EXEC_END_TIME = "yyyy-MM-dd";
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * ID       db_column: id 
     */ 	
	@Max(9223372036854775807L)
	private Long id;
	
    /**
     * 客户端ID       db_column: client_id 
     */ 	
	@NotNull @Max(9223372036854775807L)
	private Long clientId;
	
    /**
     * 执行时间       db_column: exec_time 
     */ 	
	@NotNull 
	private java.util.Date execTime;
	
    /**
     * 执行结束时间       db_column: exec_end_time 
     */ 	
	
	private java.util.Date execEndTime;
	
    /**
     * cron表达式       db_column: cron_expr 
     */ 	
	@NotBlank @Length(max=200)
	private String cronExpr;
	
    /**
     * 任务日志       db_column: tasklog 
     */ 	
	@Length(max=2147483647)
	private String tasklog;
	
    /**
     * 执行结束退出码       db_column: exit_code 
     */ 	
	@Max(9999999999L)
	private Integer exitCode;
	
    /**
     * 执行时长       db_column: exec_duration 
     */ 	
	@Max(9223372036854775807L)
	private Long execDuration;
	
	//columns END

	public CronTaskLog(){
	}

	public CronTaskLog(
		Long id
	){
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long id() {
		return this.id;
	}

	public CronTaskLog id(Long id) {
		this.id = id;
		return this;
	}
	
	public Long getClientId() {
		return this.clientId;
	}
	
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	public Long clientId() {
		return this.clientId;
	}

	public CronTaskLog clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public java.util.Date getExecTime() {
		return this.execTime;
	}
	
	public void setExecTime(java.util.Date execTime) {
		this.execTime = execTime;
	}
	
	public java.util.Date execTime() {
		return this.execTime;
	}

	public CronTaskLog execTime(java.util.Date execTime) {
		this.execTime = execTime;
		return this;
	}
	
	public java.util.Date getExecEndTime() {
		return this.execEndTime;
	}
	
	public void setExecEndTime(java.util.Date execEndTime) {
		this.execEndTime = execEndTime;
	}
	
	public java.util.Date execEndTime() {
		return this.execEndTime;
	}

	public CronTaskLog execEndTime(java.util.Date execEndTime) {
		this.execEndTime = execEndTime;
		return this;
	}
	
	public String getCronExpr() {
		return this.cronExpr;
	}
	
	public void setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
	}
	
	public String cronExpr() {
		return this.cronExpr;
	}

	public CronTaskLog cronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
		return this;
	}
	
	public String getTasklog() {
		return this.tasklog;
	}
	
	public void setTasklog(String tasklog) {
		this.tasklog = tasklog;
	}
	
	public String tasklog() {
		return this.tasklog;
	}

	public CronTaskLog tasklog(String tasklog) {
		this.tasklog = tasklog;
		return this;
	}
	
	public Integer getExitCode() {
		return this.exitCode;
	}
	
	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}
	
	public Integer exitCode() {
		return this.exitCode;
	}

	public CronTaskLog exitCode(Integer exitCode) {
		this.exitCode = exitCode;
		return this;
	}
	
	public Long getExecDuration() {
		return this.execDuration;
	}
	
	public void setExecDuration(Long execDuration) {
		this.execDuration = execDuration;
	}
	
	public Long execDuration() {
		return this.execDuration;
	}

	public CronTaskLog execDuration(Long execDuration) {
		this.execDuration = execDuration;
		return this;
	}
	
	public Boolean getExecSuccess() {
		if(exitCode == null) 
			return null;
		return exitCode == 0;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CronTaskLog == false) return false;
		CronTaskLog other = (CronTaskLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

