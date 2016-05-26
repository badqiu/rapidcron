/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */


package com.github.rapidcron.service.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.github.rapidcron.CronTaskLogDataFactory;
import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.github.rapidcron.model.*;
import com.github.rapidcron.query.*;
import com.github.rapidcron.dao.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CronTaskLogServiceImplTest {

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CronTaskLogServiceImpl service = new CronTaskLogServiceImpl();
	private CronTaskLogDao cronTaskLogDao = mock(CronTaskLogDao.class);
	
	@Rule public TestName testName = new TestName();
	
	@Before
	public void before() {
		System.out.println("\n------------------ "+testName.getMethodName()+" ----------------------\n");
		service.setCronTaskLogDao(cronTaskLogDao);
	}
	
	@Test
	public void test_create() {
		CronTaskLog obj = CronTaskLogDataFactory.newCronTaskLog();
		service.create(obj);
		
		verify(cronTaskLogDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CronTaskLog obj = CronTaskLogDataFactory.newCronTaskLog();
		service.update(obj);
		
		verify(cronTaskLogDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new Long("1"));
		
		verify(cronTaskLogDao).deleteById(new Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cronTaskLogDao.getById(new Long("1"))).thenReturn(CronTaskLogDataFactory.newCronTaskLog()); // mock方法调用
		
		CronTaskLog cronTaskLog = service.getById(new Long("1"));
		
		verify(cronTaskLogDao).getById(new Long("1")); //验证执行了该语句
		assertNotNull(cronTaskLog);
	}
	
	
}

