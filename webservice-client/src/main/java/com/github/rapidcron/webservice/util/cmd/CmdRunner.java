package com.github.rapidcron.webservice.util.cmd;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rapidcron.webservice.util.LimitOutputStream;

public class CmdRunner implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(CmdRunner.class);
	
	private String cmd;
	private ProcessMonitor processMonitor;
	private Process process;
	private Date execStartTime;
	private Date execEndTime;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	ByteArrayOutputStream errOut = new ByteArrayOutputStream();
	
	public CmdRunner(ProcessMonitor processMonitor,String cmd) {
		super();
		this.cmd = cmd;
		this.processMonitor = processMonitor; 
	}
	
	public String getCmd() {
		return cmd;
	}

	public Process getProcess() {
		return process;
	}
	
	public Date getExecStartTime() {
		return execStartTime;
	}
	
	public ByteArrayOutputStream getOut() {
		return out;
	}

	public ByteArrayOutputStream getErrOut() {
		return errOut;
	}
	
	public Date getExecEndTime() {
		return execEndTime;
	}

	@Override
	public void run() {
		try {
			execStartTime = new Date();
			out = new ByteArrayOutputStream();
			errOut = new ByteArrayOutputStream();
			
			Process process = Runtime.getRuntime().exec(cmd);
			this.process = process;
			
			InputStream processInputStream = null;
			if(process != null && process.getInputStream() != null){ 
				processInputStream = process.getInputStream();
				new AsyncOutputStreamThread(processInputStream,new TeeOutputStream(new LimitOutputStream(out,1024 * 16),System.err)).start();
			}
			
			InputStream processErrorStream = null;
			if(process != null && process.getErrorStream() != null) {
				processErrorStream = process.getErrorStream();
				new AsyncOutputStreamThread(processErrorStream,new TeeOutputStream(new LimitOutputStream(errOut,1024 * 16),System.err)).start();
			}
			
			processMonitor.addProcess(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onEnd() {
		execEndTime = new Date();
	}
}
