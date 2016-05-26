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

	public void heartbeat(String ip) {
		cronWebService.heartbeat(ip);
	}

	public String getCronContentByHostname(String hostname) {
		return cronWebService.getCronContentByHostname(hostname);
	}

	public String getCronContentByIP(String ip) {
		return cronWebService.getCronContentByIP(ip);
	}

	public String getIp() {
		if(ip == null) {
			ip = cronWebService.getIp();
		}
		return ip;
	}

	public void online(String hostname, String ip, String runUser) {
		cronWebService.online(hostname, ip, runUser);
	}

	public void log(CronTaskLogDTO obj) {
		cronWebService.log(obj);
	}
	
}
