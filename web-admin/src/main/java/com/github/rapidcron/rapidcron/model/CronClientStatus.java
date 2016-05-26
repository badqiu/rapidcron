package com.github.rapidcron.rapidcron.model;

import com.github.rapidcron.common.util.Crontab;
import com.github.rapidcron.model.CronTaskLog;

public class CronClientStatus {

	private Crontab crontab;
	private CronTaskLog cronTaskLog;

	public CronClientStatus() {
	}
	
	public CronClientStatus(Crontab crontab, CronTaskLog cronTaskLog) {
		super();
		this.crontab = crontab;
		this.cronTaskLog = cronTaskLog;
	}

	public Crontab getCrontab() {
		return crontab;
	}

	public void setCrontab(Crontab crontab) {
		this.crontab = crontab;
	}

	public CronTaskLog getCronTaskLog() {
		return cronTaskLog;
	}

	public void setCronTaskLog(CronTaskLog cronTaskLog) {
		this.cronTaskLog = cronTaskLog;
	}

}
