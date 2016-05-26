package com.github.rapidcron.webservice.client.main;

import com.github.rapid.common.util.JVMUtil;
import com.github.rapidcron.webservice.client.engine.CronEngine;

public class RapidCronClientMain {

	public static void main(String[] args) throws Exception {
		JVMUtil.lockFileForOnlyProcess(RapidCronClientMain.class);
		String webServiceUrl = System.getProperty("rapidcron_webservice_url","http://rapidcron.xsj.qinha.com");
		CronEngine cronEngine = new CronEngine(webServiceUrl);
		cronEngine.start();
	}
	
	
}
