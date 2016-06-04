package com.github.rapidcron.webservice.client.main;

import com.github.rapid.common.util.JVMUtil;
import com.github.rapidcron.webservice.client.engine.CronEngine;

public class RapidCronClientMain {

	public static void main(String[] args) throws Exception {
		JVMUtil.lockFileForOnlyProcess(RapidCronClientMain.class);
		CronEngine cronEngine = new CronEngine();
		cronEngine.start();
	}
	
}
