package com.github.rapidcron.webservice;

import com.github.rapidcron.webservice.dto.CronTaskLogDTO;

public interface CronWebService {

	public void heartbeat(String ip);
	
	public String getCronContentByHostname(String hostname);
	
	public String getCronContentByIP(String ip);
	
	public String getIp();
	
	public void online(String hostname,String ip,String runUser);
	
	public void log(CronTaskLogDTO obj);
	
}
