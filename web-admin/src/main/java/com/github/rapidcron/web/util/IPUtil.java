package com.github.rapidcron.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IPUtil {
	/**
	 * 通过request文件头拿到代理IP地址
	 * 如果代理IP超过5个，则取前面5个
	 */
	public static String getProxyIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip)) {
			return "";
		}
		
		String[] ips = ip.split(", ");
		if(ips.length > 5)
		{
			return ips[0]+", "+ips[1]+", "+ips[2]+", "+ips[3]+", "+ips[4];
		}
		return ip;
	}
	
	/**
	 * 通过request文件头拿到真实IP地址
	 */
	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip)) {
			return request.getRemoteAddr();
		}
		ip = ip.split(", ")[0].trim();
		if ("127.0.0.1".equals(ip) || !isLicitIp(ip)) {
			return request.getRemoteAddr();
		}
		return ip;
	}

	static String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
	static Pattern p = Pattern.compile(regex);
	public static boolean isLicitIp(String ip) {
		if (StringUtils.isBlank(ip))
			return false;
		Matcher m = p.matcher(ip);
		return m.find();
	}
}