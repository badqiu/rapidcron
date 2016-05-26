package com.github.rapidcron.tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//import cn.org.rapid_framework.generator.GeneratorContext;
//import cn.org.rapid_framework.generator.GeneratorFacade;
//import cn.org.rapid_framework.generator.GeneratorProperties;
//import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig;
//import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig.OperationConfig;
//import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;
//import cn.org.rapid_framework.generator.util.sqlparse.SqlParseHelper;
/**
 * 基于数据库SQL的代码生成器
 * 
 * @author badqiu
 *
 */
public class SqlGeneratorMain {
//
//	public static void main(String[] args) throws Exception {
//		GeneratorFacade g = new GeneratorFacade();
//		
//		g.getGenerator().setTemplateRootDirs(
//				"src/main/generator/template/share/custom",
//				"src/main/generator/template/sql/spring_jdbc"
////				"classpath:generator/template/rapid/table/dao_spring_jdbc",
////				"classpath:generator/template/rapid/table/dao_share_query_object",
////				"classpath:generator/template/rapid/table/dao_test"
////				"classpath:generator/template/rapid/table/service_complex",
////				"classpath:generator/template/rapid/table/web_springmvc_rest"
//				);
//		
////		g.deleteOutRootDir();	//删除生成器的输出目录
//		while(true) {
//			System.out.println("请输入要生成的[SQL],输入[;号]回车生成代码,quit退出程序");
//			String nextLine = new Scanner(System.in).useDelimiter(";").next();
//			try {
//				if("quit".equals(nextLine.trim())) {
//					break;
//				}else {
//					
////					sql.setParamType("primitive");
////					sql.setParameterClass("DemoParameterClass");
//					
//					TableConfig tableConfig = new TableConfig();
//					tableConfig.setSqlName(SqlParseHelper.getTableNamesByQuery(nextLine).iterator().next().getName());
//					HashMap hashMap = new HashMap();
//					hashMap.put("tableConfig", tableConfig);
//					hashMap.put("appName", "DemoAppName");
//					GeneratorContext.setContext(hashMap);
//					OperationConfig operationConfig = new OperationConfig();
//					operationConfig.setSql(nextLine);
//					operationConfig.setName("dosomething");
//					tableConfig.setOperations(Arrays.asList(new OperationConfig[]{operationConfig}));
//					
////					Sql sql = new SqlFactory().parseSql(nextLine);
//					Sql sql = tableConfig.getSqls().get(0);
//					g.generateBySql(sql);
//					Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot")); //打开文件夹
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
}
