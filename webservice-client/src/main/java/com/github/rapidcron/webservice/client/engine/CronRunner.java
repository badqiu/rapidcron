package com.github.rapidcron.webservice.client.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.github.rapidcron.common.util.CronUtil;
import com.github.rapidcron.common.util.Crontab;
import com.github.rapidcron.webservice.client.CronWebServiceClient;
import com.github.rapidcron.webservice.dto.CronTaskLogDTO;
import com.github.rapidcron.webservice.util.cmd.CmdRunner;
import com.github.rapidcron.webservice.util.cmd.ProcessListener;
import com.github.rapidcron.webservice.util.cmd.ProcessMonitor;

public class CronRunner implements InitializingBean{
	private static Logger logger = LoggerFactory.getLogger(CronRunner.class);
	
	private ScheduledExecutorService localExecutor;
	private CronWebServiceClient cronWebServiceClient;
	
	private ProcessMonitor processMonitor = new ProcessMonitor();
	
	private int cronThreadPoolSize = 100;
	
	private String preCronContent = null;
	private String userHomeRapidConfFile = System.getProperty("user.home")+"/rapidcron/rapidcron.conf";
	private String cronFilePaths[] = new String[]{
			"/etc/rapidcron/rapidcron.conf",
			userHomeRapidConfFile
	};
	
	private List<ScheduledFuture> preScheduledFutureList = new ArrayList<ScheduledFuture>();
	
	public void setCronWebServiceClient(CronWebServiceClient cronWebServiceClient) {
		this.cronWebServiceClient = cronWebServiceClient;
	}

//	public List<Crontab> getCronList() throws IOException {
//		String cronContent = getCronContent();
//		return CronUtil.parseCronMultiLine(cronContent);
//	}

	private String getCronContent() throws IOException {
		String cronContent = null;
		try {
			cronContent = getCronContentFromRemote();
		}catch(Exception e) {
			logger.info("error getCronContentFromRemote,ignore,try read from local");
		}
		if(StringUtils.isBlank(cronContent)) {
			cronContent = getCronContentFromLocalFile();
		}
		return cronContent;
	}

	private void updateLocalCronFile(String cronContent) throws IOException {
		File file = new File(userHomeRapidConfFile);
		file.getParentFile().mkdirs();
		FileUtils.writeStringToFile(file, cronContent);
	}
	
	private String getCronContentFromLocalFile() throws IOException {
		for(String path : cronFilePaths) {
			File file = new File(path);
			if(!file.exists()) {
				continue;
			}
			String fileContent = FileUtils.readFileToString(file);
			return fileContent;
		}
		return null;
	}

	private String getCronContentFromRemote() {
		String ip = cronWebServiceClient.getIp();
		String cronContent = cronWebServiceClient.getCronContentByIP(ip);
		return cronContent;
	}
	
	public void autoRecronJobs() throws Exception {
		String curCronContent = getCronContent();
		if(StringUtils.isNotBlank(curCronContent) && !curCronContent.equals(preCronContent)) {
			updateLocalCronFile(curCronContent);
			preCronContent = curCronContent;
			startCronJobs(curCronContent);
		}
	}
	
	public void startCronJobs(String cronContent) throws IOException, InterruptedException {
		logger.info("startCronJobs exec,cronContent:\n"+cronContent);
		if(this.localExecutor != null) {
			stopCronJobs();
		}
		this.localExecutor = Executors.newScheduledThreadPool(cronThreadPoolSize);
		
		ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler();
		List<Crontab> cronList = CronUtil.parseCronMultiLine(cronContent);
		for(Crontab crontab : cronList) {
			CronTrigger trigger = new CronTrigger("1 " + crontab.getCron());
			CmdRunner cmdRunner = new CmdRunner(processMonitor,crontab.getCmd());
			ScheduledFuture future = taskScheduler.schedule(cmdRunner, trigger);
			preScheduledFutureList.add(future);
			logger.info("start cron job:"+crontab);
		}
	}

	private void stopCronJobs() throws InterruptedException {
		for(ScheduledFuture future : preScheduledFutureList){
			future.cancel(true);
		}
		preScheduledFutureList = new ArrayList<ScheduledFuture>();
		this.localExecutor.shutdownNow();
		this.localExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		processMonitor.addProcessListener(new ProcessListener() {
			@Override
			public void onProcessExit(CmdRunner runner) {
				Process p = runner.getProcess();
				int exitCode = p.exitValue();
				long execDuration = runner.getExecEndTime().getTime() - runner.getExecStartTime().getTime();
				String tasklog = runner.getOut().toString() +"\n"+ runner.getErrOut().toString();
				
				CronTaskLogDTO dto = new CronTaskLogDTO();
				dto.setIp(cronWebServiceClient.getIp());
				dto.setCronExpr(runner.getCmd());
				dto.setExecTime(runner.getExecStartTime());
				dto.setExitCode(exitCode);
				dto.setExecDuration(execDuration);
				dto.setTasklog(tasklog);
				cronWebServiceClient.log(dto);
			}
		});
		
		new Thread(processMonitor,"processMonitor").start();
	}
}
