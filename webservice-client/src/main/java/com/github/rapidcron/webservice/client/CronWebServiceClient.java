package com.github.rapidcron.webservice.client;

import java.util.Date;

import com.github.rapidcron.webservice.CronWebService;
import com.github.rapidcron.webservice.dto.CronTaskLogDTO;

public class CronWebServiceClient {

	private CronWebService  cronWebService;
	
	private String ip;
	
	public void setCronWebService(CronWebService cronWebService) {
		this.cronWebService = cronWebService;
	}

	public void heartbeat(String mid) {
		cronWebService.heartbeat(mid);
	}

	public String getCronContentByHostname(String hostname) {
		return cronWebService.getCronContentByHostname(hostname);
	}

	public String getCronContentByMid(String mid) {
		return cronWebService.getCronContentByMid(mid);
	}

	public String getIp() {
		if(ip == null) {
			ip = cronWebService.getIp();
		}
		return ip;
	}

	public void online(String hostname, String ip, String runUser,String mid,String crontab) {
		//TODO crontab也显示出来
		cronWebService.online(hostname, ip, runUser,mid);
	}

	public void log(CronTaskLogDTO obj) {
		cronWebService.log(obj);
	}
	
}
