<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static junit.framework.Assert.*;

<#include "/java_imports.include">

/**
<#include "/java_description.include">
 */
public class ${className}DaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private ${className}Dao dao;
	
	@Autowired
	public void set${className}Dao(${className}Dao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/${className}.xml",
//		                    "classpath:testdata/${className}_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		${className}Query query = ${className}DataFactory.new${className}Query();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		dao.insert(${className}DataFactory.new${className}());
	}
	
	@Test
	public void test_update() {
		dao.update(${className}DataFactory.new${className}());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(<@generateArgumentsWithRandomValue table.pkColumns/>);
	}
	
	@Test
	public void test_getById() {
		dao.getById(<@generateArgumentsWithRandomValue table.pkColumns/>);
	}
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Test
	public void test_getBy${column.columnName}() {
		dao.getBy${column.columnName}(new ${column.javaType}("1"));
	}
	
	</#if>
	</#list>
	
}

