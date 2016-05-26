package com.github.rapidcron.common.util;

public class Crontab {

	private String cron;
	private String cmd;

	public Crontab() {
	}
	
	public Crontab(String cron, String cmd) {
		super();
		this.cron = cron;
		this.cmd = cmd;
	}


	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public String toString() {
		return "Crontab [cron=" + cron + ", cmd=" + cmd + "]";
	}

}
