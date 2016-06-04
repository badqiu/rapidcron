package com.github.rapidcron.common.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.rapid.common.util.RegexUtil;

public class CronUtil {

	public static List<Crontab> parseCronMultiLine(String cronContent) {
		if(StringUtils.isBlank(cronContent)) return Collections.EMPTY_LIST;
		
		try {
			List<String> lines = IOUtils.readLines(new StringReader(cronContent));
			List<Crontab> result = new ArrayList<Crontab>();
			for(String line : lines) {
				if(StringUtils.isBlank(line))
					continue;
				if(line.trim().startsWith("#")) {
					continue;
				}
				
				Crontab crontab = parseCronLine(line);
				if(crontab != null) {
					result.add(crontab);
				}
			}
			return result;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// ([\w\*]+\s+[\w\*]+\s+[\w\*]+\s+[\w\*]+\s+[\w\*]+\s+)(.*);
	public static Crontab parseCronLine(String line) {
		if(StringUtils.isBlank(line)) return null;
		if(line.trim().startsWith("#")) return null;
		
		String trimLine = line.trim();
		String regex = "([\\w\\*/]+\\s+[\\w\\*/]+\\s+[\\w\\*/]+\\s+[\\w\\*/]+\\s+[\\w\\*/]+\\s+)(.*)";
		String cron = RegexUtil.findByRegexGroup(trimLine, regex, 1);
		String cmd = RegexUtil.findByRegexGroup(trimLine, regex, 2);
		return new Crontab(cron,cmd);
	}
	
}
