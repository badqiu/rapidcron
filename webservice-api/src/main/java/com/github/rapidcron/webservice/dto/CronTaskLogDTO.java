package com.github.rapidcron.webservice.dto;

public class CronTaskLogDTO {
	
	private String mid;
	
	private String ip;
	
	/**
	 * 执行时间 db_column: exec_time
	 */

	private java.util.Date execTime;

	/**
	 * cron表达式 db_column: cron_expr
	 */
	private String cronExpr;

	/**
	 * 任务日志 db_column: tasklog
	 */
	private String tasklog;

	/**
	 * 执行结束退出码 db_column: exit_code
	 */
	private Integer exitCode;
	/**
	 * 执行时长
	 */
	private long execDuration;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public java.util.Date getExecTime() {
		return execTime;
	}

	public void setExecTime(java.util.Date execTime) {
		this.execTime = execTime;
	}

	public String getCronExpr() {
		return cronExpr;
	}

	public void setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
	}

	public String getTasklog() {
		return tasklog;
	}

	public void setTasklog(String tasklog) {
		this.tasklog = tasklog;
	}

	public Integer getExitCode() {
		return exitCode;
	}

	public void setExitCode(Integer exitCode) {
		this.exitCode = exitCode;
	}

	public long getExecDuration() {
		return execDuration;
	}

	public void setExecDuration(long execDuration) {
		this.execDuration = execDuration;
	}

}
