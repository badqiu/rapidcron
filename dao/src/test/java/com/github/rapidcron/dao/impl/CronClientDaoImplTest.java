/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao.impl;

import com.github.rapidcron.CronClientDataFactory;
import com.github.rapid.common.util.page.Page;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;






import static junit.framework.Assert.*;

import com.github.rapidcron.model.*;
import com.github.rapidcron.query.*;
import com.github.rapidcron.dao.*;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/*.xml" })
@Transactional
public class CronClientDaoImplTest {
	
	@Rule public TestName testName = new TestName();
	
	private CronClientDao dao;
	
	@Before
	public void before() {
		System.out.println("\n------------------ "+testName.getMethodName()+" ----------------------\n");
	}
	
	@Autowired
	public void setCronClientDao(CronClientDao dao) {
		this.dao = dao;
	}

	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CronClient.xml",
//		                    "classpath:testdata/CronClient_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CronClientQuery query = CronClientDataFactory.newCronClientQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		CronClient obj = CronClientDataFactory.newCronClient();
		obj.setClientId(null);
		dao.insert(obj);
	}
	
	@Test
	public void test_update() {
		dao.update(CronClientDataFactory.newCronClient());
	}
	
	@Test
	public void test_updateLastHearbeatTime() {
		dao.updateLastHearbeatTime("127.0.0.1");
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(new Long("1"));
	}
	
	@Test
	public void test_getById() {
		dao.getById(new Long("1"));
	}
	
	@Test
	public void test_getByHostname() {
		dao.getByHostname("badqiu");
	}
	
	@Test
	public void test_getByIP() {
		dao.getByIP("192.168.1.1");
	}
}

