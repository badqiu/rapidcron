package com.github.rapidcron.webservice.client.engine;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.rapidcron.common.util.SystemUtil;
import com.github.rapidcron.webservice.client.CronWebServiceClient;
import com.github.rapidcron.webservice.util.ThreadUtil;

public class CronEngine {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/*.xml");
	CronWebServiceClient cronWebServiceClient;
	private String webServiceUrl = null;
	public CronEngine() {
	}
	
	public CronEngine(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public void start() throws Exception {
		cronWebServiceClient = context.getBean(CronWebServiceClient.class);
		
		startHeartbeatTask();
		startCronRunner();
	}

	private void online() throws UnknownHostException {
		String hostname = InetAddress.getLocalHost().getHostName();
		String ip = cronWebServiceClient.getIp();
		String runUser = System.getProperty("user.name");
		String mid = SystemUtil.getDeviceId();
		cronWebServiceClient.online(hostname, ip, runUser,mid);
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
