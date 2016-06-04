package com.github.rapidcron.webservice.client.engine;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.rapidcron.common.util.SystemUtil;
import com.github.rapidcron.webservice.client.CronWebServiceClient;
import com.github.rapidcron.webservice.util.ThreadUtil;
import com.github.rapidcron.webservice.util.cmd.ProcessUtil;

public class CronEngine {

	private static Logger logger = LoggerFactory.getLogger(CronEngine.class);
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/*.xml");
	CronWebServiceClient cronWebServiceClient;
	public CronEngine() {
	}
	

	public void start() throws Exception {
		cronWebServiceClient = context.getBean(CronWebServiceClient.class);
		
		startHeartbeatTask();
		startCronRunner();
	}

	private void online() throws IOException, InterruptedException {
		String hostname = InetAddress.getLocalHost().getHostName();
		String ip = cronWebServiceClient.getIp();
		String runUser = System.getProperty("user.name");
		String mid = SystemUtil.getDeviceId();
		String crontab = getUserCrontab();
		cronWebServiceClient.online(hostname, ip, runUser,mid,crontab);
	}


	private String getUserCrontab() throws IOException, InterruptedException {
		try {
			if(!SystemUtils.IS_OS_WINDOWS) {
				String crontab = ProcessUtil.execCmdForTaskExecResult("crontab -l", Integer.MAX_VALUE).getLog();
				return crontab;
			}
			return null;
		}catch(Exception e) {
			logger.warn("error on get user crontab by cmd:crontab -l",e);
			return null;
		}
	}

	private void startCronRunner() throws Exception, InterruptedException {
		CronRunner runner = new CronRunner();
		runner.setCronWebServiceClient(cronWebServiceClient);
		runner.afterPropertiesSet();
		while(true) {
			try {
				runner.autoRecronJobs();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				ThreadUtil.sleepSeconds(10);
			}
		}
	}

	private void startHeartbeatTask() {
		Runnable heartbeatTask = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						online();
						break;
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						ThreadUtil.sleepSeconds(5);
					}
				}
				String mid = SystemUtil.getDeviceId();
				while(true) {
					try {
						cronWebServiceClient.heartbeat(mid);
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						ThreadUtil.sleepSeconds(5);
					}
				}
			}
		};
		new Thread(heartbeatTask,"cron_heartbeat").start();
	}

}
