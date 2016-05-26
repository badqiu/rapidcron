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

import com.sun.org.apache.bcel.internal.Constants;



/**
 * tableName: cron_client [CronClient] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CronClient  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd";
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 客户端ID       db_column: client_id 
     */ 	
	@Max(9223372036854775807L)
	private Long clientId;
	
    /**
     * 主机名       db_column: hostname 
     */ 	
	@Length(max=50)
	private String hostname;
	
    /**
     * IP       db_column: ip 
     */ 	
	@Length(max=100)
	private String ip;
	
    /**
     * 备注       db_column: remarks 
     */ 	
	@Length(max=200)
	private String remarks;
	
    /**
     * 运行用户       db_column: run_user 
     */ 	
	@Length(max=50)
	private String runUser;
	
    /**
     * cron内容       db_column: cron_content 
     */ 	
	@Length(max=4000)
	private String cronContent;
	
    /**
     * 在线状态       db_column: client_status 
     */ 	
	@Length(max=30)
	private String clientStatus;
	
    /**
     * 操作人员       db_column: operator 
     */ 	
	@Length(max=50)
	private String operator;
	
    /**
     * 创建时间       db_column: create_time 
     */ 	
	
	private java.util.Date createTime;
	
    /**
     * 最后更新时间       db_column: update_time 
     */ 	
	
	private java.util.Date updateTime;
	
	private Date lastHearbeatTime;
	//columns END

	public CronClient(){
	}

	public CronClient(
		Long clientId
	){
		this.clientId = clientId;
	}

	public Long getClientId() {
		return this.clientId;
	}
	
	public CronClient setClientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public Long clientId() {
		return this.clientId;
	}

	public CronClient clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public String getHostname() {
		return this.hostname;
	}
	
	public CronClient setHostname(String hostname) {
		this.hostname = hostname;
		return this;
	}
	
	public String hostname() {
		return this.hostname;
	}

	public CronClient hostname(String hostname) {
		this.hostname = hostname;
		return this;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public CronClient setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	public String ip() {
		return this.ip;
	}

	public CronClient ip(String ip) {
		this.ip = ip;
		return this;
	}
	
	public String getRemarks() {
		return this.remarks;
	}
	
	public CronClient setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
	
	public String remarks() {
		return this.remarks;
	}

	public CronClient remarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
	
	public String getRunUser() {
		return this.runUser;
	}
	
	public CronClient setRunUser(String runUser) {
		this.runUser = runUser;
		return this;
	}
	
	public String runUser() {
		return this.runUser;
	}

	public CronClient runUser(String runUser) {
		this.runUser = runUser;
		return this;
	}
	
	public String getCronContent() {
		return this.cronContent;
	}
	
	public CronClient setCronContent(String cronContent) {
		this.cronContent = cronContent;
		return this;
	}
	
	public String cronContent() {
		return this.cronContent;
	}

	public CronClient cronContent(String cronContent) {
		this.cronContent = cronContent;
		return this;
	}
	
	public String getClientStatus() {
		return this.clientStatus;
	}
	
	public CronClient setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
		return this;
	}
	
	public String clientStatus() {
		return this.clientStatus;
	}

	public CronClient clientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
		return this;
	}
	
	public boolean isOnline() {
		if(getLastHearbeatTime() == null) return false;
		
		long interval = Math.abs(System.currentTimeMillis() - getLastHearbeatTime().getTime());
		if(interval <= ( 1000 * com.github.rapidcron.Constants.HEARTBEAT_INTERVAL_SECONDS + 3000) ) {
			return true;
		}
		return false;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public CronClient setOperator(String operator) {
		this.operator = operator;
		return this;
	}
	
	public String operator() {
		return this.operator;
	}

	public CronClient operator(String operator) {
		this.operator = operator;
		return this;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public CronClient setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public java.util.Date createTime() {
		return this.createTime;
	}

	public CronClient createTime(java.util.Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public CronClient setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public java.util.Date updateTime() {
		return this.updateTime;
	}

	public CronClient updateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public Date getLastHearbeatTime() {
		return lastHearbeatTime;
	}

	public void setLastHearbeatTime(Date lastHearbeatTime) {
		this.lastHearbeatTime = lastHearbeatTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getClientId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CronClient == false) return false;
		CronClient other = (CronClient)obj;
		return new EqualsBuilder()
			.append(getClientId(),other.getClientId())
			.isEquals();
	}
}

