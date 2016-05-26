package com.github.rapidcron.tools;


import java.util.Scanner;

//import cn.org.rapid_framework.generator.GeneratorFacade;
//import cn.org.rapid_framework.generator.GeneratorProperties;
/**
 * 基于数据库表的代码生成器
 * 
 * @author badqiu
 *
 */
public class TableGeneratorMain {
//	/**
//	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
//	 */
//	public static void main(String[] args) throws Exception {
//		GeneratorFacade g = new GeneratorFacade();
//		
//		g.getGenerator().setTemplateRootDirs(
//				"src/main/generator/template/share/custom",
//				"src/main/generator/template/dao/spring_jdbc"
////				"classpath:generator/template/rapid/table/dao_spring_jdbc",
////				"classpath:generator/template/rapid/table/dao_share_query_object",
////				"classpath:generator/template/rapid/table/dao_test"
////				"classpath:generator/template/rapid/table/service_complex",
////				"classpath:generator/template/rapid/table/web_springmvc_rest"
//				);
//		
//		g.deleteOutRootDir();	//删除生成器的输出目录
//		while(true) {
//			System.out.println("请输入要生成的[表名],输入[*号]搜索数据库所有表并生成,[回车]结束运行:");
//			String nextLine = new Scanner(System.in).nextLine();
//			if("".equals(nextLine.trim())) {
//				break;
//			}else {
//				try {
//					g.generateByTable(nextLine);	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//					Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot")); //打开文件夹
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
////		g.deleteByTable("table_name", "template"); //删除生成的文件
//		
//	}
}
