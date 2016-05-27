package com.github.rapidcron.webservice.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.rapid.common.beanutils.PropertyUtils;
import com.github.rapid.common.rpc.server.RPCContext;
import com.github.rapidcron.enums.ClientStatus;
import com.github.rapidcron.model.CronClient;
import com.github.rapidcron.model.CronTaskLog;
import com.github.rapidcron.service.CronClientService;
import com.github.rapidcron.service.CronTaskLogService;
import com.github.rapidcron.web.util.IPUtil;
import com.github.rapidcron.webservice.CronWebService;
import com.github.rapidcron.webservice.dto.CronTaskLogDTO;

public class CronWebServiceImpl implements CronWebService{

	@Autowired
	private CronClientService cronClientService;
	@Autowired
	private CronTaskLogService cronTaskLogService;
	
	public void setCronClientService(CronClientService cronClientService) {
		this.cronClientService = cronClientService;
	}
	
	public void setCronTaskLogService(CronTaskLogService cronTaskLogService) {
		this.cronTaskLogService = cronTaskLogService;
	}

	@Override
	public void heartbeat(String ip) {
		if(StringUtils.isBlank(ip)) {
			ip = RPCContext.getRequest().getRemoteHost();
		}
//		CronClient cronClient = cronClientService.getByIP(ip);
//		Assert.notNull(cronClient,"not found cronClient by ip:"+ip);
//		cronClient.setLastHearbeatTime(new Date());
		cronClientService.updateLastHearbeatTime(ip);
	}

	@Override
	public String getCronContentByHostname(String hostname) {
		CronClient client = cronClientService.getByHostname(hostname);
		return client.getCronContent();
	}

	@Override
	public String getCronContentByIP(String ip) {
		CronClient client = cronClientService.getByIP(ip);
		return client.getCronContent();
	}

	@Override
	public String getIp() {
		return IPUtil.getRealIp(RPCContext.getRequest());
	}

	@Override
	public void online(String hostname, String ip, String runUser) {
//		String hostIp = RPCContext.getRequest().getRemoteHost();
		CronClient cronClient = cronClientService.getByIP(ip);
		if(cronClient == null) {
			cronClient = new CronClient();
			cronClient.setHostname(hostname);
			cronClient.setIp(ip);
			cronClient.setRunUser(runUser);
			cronClient.setLastHearbeatTime(new Date());
			cronClient.setClientStatus(ClientStatus.ONLINE.getCode());
			cronClientService.create(cronClient);
			return;
		}
		
		cronClient.setLastHearbeatTime(new Date());
		cronClient.setClientStatus(ClientStatus.ONLINE.getCode());
		cronClientService.update(cronClient);
	}

	@Override
	public void log(CronTaskLogDTO obj) {
		CronClient client = cronClientService.getByIP(obj.getIp());
		CronTaskLog tasklog = new CronTaskLog();
		PropertyUtils.copyProperties(tasklog, obj);
		tasklog.setClientId(client.getClientId());
		
		cronTaskLogService.create(tasklog);
	}

}
