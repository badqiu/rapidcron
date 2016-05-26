package com.github.rapidcron.webservice.util.cmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rapidcron.webservice.util.ThreadUtil;

public class ProcessMonitor implements Runnable{
	private static Logger logger = LoggerFactory.getLogger(ProcessMonitor.class);
	
	private List<CmdRunner> processList = new LinkedList<CmdRunner>();
	
	private List<ProcessListener> processListener = new ArrayList<ProcessListener>();
	
	public void addProcess(CmdRunner p) {
		processList.add(p);
	}
	
	public void addProcessListener(ProcessListener listener) {
		processListener.add(listener);
	}
	
	public void monitor() {
		ArrayList<CmdRunner> arrayList = new ArrayList<CmdRunner>(processList);
		for(CmdRunner item : arrayList) {
			if(item.getProcess().isAlive()) {
				continue;
			}
			processList.remove(item);
			item.onEnd();
			for(ProcessListener listener : processListener) {
				listener.onProcessExit(item);
			}
		}
	}

	@Override
	public void run() {
		logger.info("process monitor thread started");
		while(true) {
			try {
				monitor();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				ThreadUtil.sleep(500);
			}
		}
	}
}
