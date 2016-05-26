/*
 * Copyright [rapid-framework.com]
 * Web Site: http://www.rapid-framework.com
 * Since 2005 - 2016
 */

package com.github.rapidcron.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.rapid.common.util.page.Page;
import com.github.rapidcron.CronTaskLogDataFactory;
import com.github.rapidcron.dao.CronTaskLogDao;
import com.github.rapidcron.model.CronTaskLog;
import com.github.rapidcron.query.CronTaskLogQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/*.xml" })
@Transactional
public class CronTaskLogDaoImplTest {
	
	@Rule public TestName testName = new TestName();
	
	private CronTaskLogDao dao;
	
	@Before
	public void before() {
		System.out.println("\n------------------ "+testName.getMethodName()+" ----------------------\n");
	}
	
	@Autowired
	public void setCronTaskLogDao(CronTaskLogDao dao) {
		this.dao = dao;
	}

	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CronTaskLog.xml",
//		                    "classpath:testdata/CronTaskLog_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CronTaskLogQuery query = CronTaskLogDataFactory.newCronTaskLogQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		dao.insert(CronTaskLogDataFactory.newCronTaskLog());
	}
	
	@Test
	public void test_update() {
		dao.update(CronTaskLogDataFactory.newCronTaskLog());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(1);
	}
	
	@Test
	public void test_getById() {
		dao.getById(1);
	}
	
	@Test
	public void getLastTaskLogByCmd() {
		CronTaskLog obj = dao.getLastTaskLogByCmd(5, "cmd.exe /c echo \"hello world 444 \"");
		assertNotNull(obj);
	}
}

