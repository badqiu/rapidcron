package com.github.rapidcron.webservice;

import com.github.rapidcron.webservice.dto.CronTaskLogDTO;

public interface CronWebService {

	public void heartbeat(String mid);
	
	public String getCronContentByHostname(String hostname);
	
	public String getCronContentByMid(String mid);
	
	public String getIp();
	
	public void online(String hostname,String ip,String runUser,String mid);
	
	public void log(CronTaskLogDTO obj);
	
}
