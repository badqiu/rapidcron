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

import com.github.rapidcron.CronClientDataFactory;
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
public class CronClientServiceImplTest {

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CronClientServiceImpl service = new CronClientServiceImpl();
	private CronClientDao cronClientDao = mock(CronClientDao.class);
	
	@Rule public TestName testName = new TestName();
	
	@Before
	public void before() {
		System.out.println("\n------------------ "+testName.getMethodName()+" ----------------------\n");
		service.setCronClientDao(cronClientDao);
	}
	
	@Test
	public void test_create() {
		CronClient obj = CronClientDataFactory.newCronClient();
		service.create(obj);
		
		verify(cronClientDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CronClient obj = CronClientDataFactory.newCronClient();
		service.update(obj);
		
		verify(cronClientDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new Long("1"));
		
		verify(cronClientDao).deleteById(new Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cronClientDao.getById(new Long("1"))).thenReturn(CronClientDataFactory.newCronClient()); // mock方法调用
		
		CronClient cronClient = service.getById(new Long("1"));
		
		verify(cronClientDao).getById(new Long("1")); //验证执行了该语句
		assertNotNull(cronClient);
	}
	
	
}

