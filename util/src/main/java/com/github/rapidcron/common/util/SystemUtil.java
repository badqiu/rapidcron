package com.github.rapidcron.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class SystemUtil {

	private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);
	
	private static String deviceId = null;
	
	public synchronized static String getDeviceId()  {
		if(deviceId == null) {
			try {
				deviceId = getDeviceId0();
			} catch (IOException e) {
				throw new RuntimeException("cannot get uuid",e);
			}
		}
		return deviceId;
	}

	private synchronized static String getDeviceId0() throws IOException {
		if (SystemUtils.IS_OS_WINDOWS) {
			return getUUIDByLocalFile();
		} else if (SystemUtils.IS_OS_LINUX) {
			try {
				return getUUIDByDmidecode();
			} catch (Exception e) {
				e.printStackTrace();
				return getUUIDByLocalFile();
			}
		}
		return null;
	}
	
	private static String getUUIDByLocalFile() throws IOException {
		String userHome = System.getProperty("user.home");
		File file = new File(userHome,".machine_uuid");
		if(file.exists()) {
			return FileUtils.readFileToString(file);
		}else {
			String uuid = StringUtils.replace(UUID.randomUUID().toString(),"-","");
			FileUtils.writeStringToFile(file, uuid);
			return uuid;
		}
	}

	private static String getUUIDByDmidecode() throws IOException,
			InterruptedException {
		String cmd = "dmidecode | grep -i uuid";
		ProcessOutput output = execForProcessOutput(cmd);
		String strOut = output.getOut();
		int index = strOut.toLowerCase().indexOf("uuid:");
		String uuid = strOut.toLowerCase().substring(index + 5);
		String result = uuid.trim();
		Assert.hasText(result,"not found uuid by 'dmidecode' program");
		return result;
	}

	private static ProcessOutput execForProcessOutput(String cmd)
			throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec(cmd);
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			throw new RuntimeException("exec cmd error,exitCode:" + exitCode
					+ " cmd:" + cmd);
		}
		String out = IOUtils.toString(process.getInputStream());
		String errOut = IOUtils.toString(process.getErrorStream());
		ProcessOutput po = new ProcessOutput();
		po.setOut(out);
		po.setErrOut(errOut);
		return po;
	}
}
