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
 * [CronClient] 查询对象
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CronClientQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 客户端ID */
	private Long clientId;
	/** 主机名 */
	private String hostname;
	/** IP */
	private String ip;
	/** 备注 */
	private String remarks;
	/** 运行用户 */
	private String runUser;
	/** cron内容 */
	private String cronContent;
	/** 在线状态 */
	private String clientStatus;
	/** 操作人员 */
	private String operator;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;

	public Long getClientId() {
		return this.clientId;
	}
	
	public CronClientQuery setClientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public Long clientId() {
		return this.clientId;
	}

	public CronClientQuery clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}
	
	public String getHostname() {
		return this.hostname;
	}
	
	public CronClientQuery setHostname(String hostname) {
		this.hostname = hostname;
		return this;
	}
	
	public String hostname() {
		return this.hostname;
	}

	public CronClientQuery hostname(String hostname) {
		this.hostname = hostname;
		return this;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public CronClientQuery setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	public String ip() {
		return this.ip;
	}

	public CronClientQuery ip(String ip) {
		this.ip = ip;
		return this;
	}
	
	public String getRemarks() {
		return this.remarks;
	}
	
	public CronClientQuery setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
	
	public String remarks() {
		return this.remarks;
	}

	public CronClientQuery remarks(String remarks) {
		this.remarks = remarks;
		return this;
	}
	
	public String getRunUser() {
		return this.runUser;
	}
	
	public CronClientQuery setRunUser(String runUser) {
		this.runUser = runUser;
		return this;
	}
	
	public String runUser() {
		return this.runUser;
	}

	public CronClientQuery runUser(String runUser) {
		this.runUser = runUser;
		return this;
	}
	
	public String getCronContent() {
		return this.cronContent;
	}
	
	public CronClientQuery setCronContent(String cronContent) {
		this.cronContent = cronContent;
		return this;
	}
	
	public String cronContent() {
		return this.cronContent;
	}

	public CronClientQuery cronContent(String cronContent) {
		this.cronContent = cronContent;
		return this;
	}
	
	public String getClientStatus() {
		return this.clientStatus;
	}
	
	public CronClientQuery setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
		return this;
	}
	
	public String clientStatus() {
		return this.clientStatus;
	}

	public CronClientQuery clientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
		return this;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public CronClientQuery setOperator(String operator) {
		this.operator = operator;
		return this;
	}
	
	public String operator() {
		return this.operator;
	}

	public CronClientQuery operator(String operator) {
		this.operator = operator;
		return this;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public CronClientQuery setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
		return this;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public CronClientQuery setCreateTimeEnd(java.util.Date value) {
		this.createTimeEnd = value;
		return this;
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public CronClientQuery setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
		return this;
	}	
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public CronClientQuery setUpdateTimeEnd(java.util.Date value) {
		this.updateTimeEnd = value;
		return this;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

