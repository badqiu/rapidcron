package com.github.rapidcron.tools;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 用于将一条字符串， 生成引号包含的JAVA多行字符串
 * 输入: 
 * 	select * from user 
 *  where username = ?
 * 
 * 输出:
 *  "select * from user" +
 *  "where username = ?"
 *  
 * @author badqiu
 *
 */
public class SqlAutoWrapMain {
	
	public static void main(String[] args) {
		
		
		while(true) {
			System.out.println("请输入需要转换为JAVA多行字符串的字符串,并以'分号'结束输入");
			String sql = new Scanner(System.in).useDelimiter(";").next();
			
			System.out.println("--- BEGIN ----------------------------------------------------");
			System.out.println(convertString2JavaMultiLines(sql));
			System.out.println("\n---- END ----------------------------------------------------\n");
		}
	}

	public static String convertString2JavaMultiLines(String sql) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for(String line : StringUtils.split(sql,"\n\r")) {
			if(!isFirst) {
				sb.append(" + \n");
			}else {
				isFirst = false;
			}
			sb.append("\""+line+" \"");
		}
		return sb.toString();
	}
	
	public static void convertJavaMultiLines2String(String sql) {
		boolean isFirst = true;
		for(String line : StringUtils.split(sql,"\n\r")) {
			if(!isFirst) {
				System.out.println(" + ");
			}else {
				isFirst = false;
			}
			System.out.print("\""+line+" \"");
		}
	}
}
