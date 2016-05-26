package com.github.rapidcron.tools;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * 开发调试使用的 Jetty Server
 * @author badqiu
 *
 */
public class WebAdminJettyServer {
	
	public static void main(String[] args) throws Exception {
		Server server = createServerInSource(7070, "/");
		server.start();
	}
	
	/**
	 * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 * @throws Exception 
	 */
	public static Server createServerInSource(int port, String contextPath) throws Exception {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		//webContext.setDefaultsDescriptor("jetty-webdefault.xml"); // 避免windows lock,设置useFileMappedBuffer=false
		webContext.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false"); // 避免windows lock,导致文件无法修改的问题,设置useFileMappedBuffer=false
		
		//设置这个，可以加载jar的 jsp tld 文件(jsp tag file)
		webContext.setClassLoader(new WebAppClassLoader(webContext));
		webContext.setParentLoaderPriority(true);
		
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}


}
