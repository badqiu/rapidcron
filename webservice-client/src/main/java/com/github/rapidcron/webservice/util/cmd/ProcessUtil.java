package com.github.rapidcron.webservice.util.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.rapidcron.webservice.util.LimitOutputStream;

public class ProcessUtil {
	private static Logger logger = LoggerFactory.getLogger(ProcessUtil.class);
	
	public static TaskExecResult execCmdForTaskExecResult(String cmd,int maxOutputLength) throws IOException, InterruptedException {
		logger.info("exec cmd:"+cmd);
		long start = System.currentTimeMillis();
		Process process = Runtime.getRuntime().exec(cmd);
		
		InputStream processInputStream = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if(process != null && process.getInputStream() != null){ 
			processInputStream = process.getInputStream();
			new AsyncOutputStreamThread(processInputStream,new TeeOutputStream(new LimitOutputStream(out,maxOutputLength),System.err)).start();
		}
		
		InputStream processErrorStream = null;
		ByteArrayOutputStream errOut = new ByteArrayOutputStream();
		if(process != null && process.getErrorStream() != null) {
			processErrorStream = process.getErrorStream();
			new AsyncOutputStreamThread(processErrorStream,new TeeOutputStream(new LimitOutputStream(errOut,maxOutputLength),System.err)).start();
		}
		
		int exitValue = process.waitFor();
		
		IOUtils.closeQuietly(processInputStream);
		IOUtils.closeQuietly(processErrorStream);
		long cost = System.currentTimeMillis() - start;
		logger.info("exec exitValue:" + exitValue + "  with cmd:"+cmd+" costSeconds:" + (cost / 1000));
		return new TaskExecResult(exitValue,out.toString(),errOut.toString());
	}
}
