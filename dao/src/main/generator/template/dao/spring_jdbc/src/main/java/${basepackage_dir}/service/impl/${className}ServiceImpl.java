<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ${basepackage}.service.${className}Service;
<#include "/java_imports.include">

/**
 * [${table.tableAlias}] 的业务操作实现类
 * 
<#include "/java_description.include">
 */
@Service("${classNameLower}Service")
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

	protected static final Logger log = LoggerFactory.getLogger(${className}ServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private ${className}Dao ${classNameLower}Dao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void set${className}Dao(${className}Dao dao) {
		this.${classNameLower}Dao = dao;
	}
	
	/** 
	 * 创建${className}
	 **/
	public ${className} create(${className} ${classNameLower}) {
	    Assert.notNull(${classNameLower},"'${classNameLower}' must be not null");
	    initDefaultValuesForCreate(${classNameLower});
	    new ${className}Checker().checkCreate${className}(${classNameLower});
	    ${classNameLower}Dao.insert(${classNameLower});
	    return ${classNameLower};
	}
	
	/** 
	 * 更新${className}
	 **/	
    public ${className} update(${className} ${classNameLower}) {
        Assert.notNull(${classNameLower},"'${classNameLower}' must be not null");
        new ${className}Checker().checkUpdate${className}(${classNameLower});
        ${classNameLower}Dao.update(${classNameLower});
        return ${classNameLower};
    }	
    
	/** 
	 * 删除${className}
	 **/
    public void removeById(<@generateArguments table.pkColumns/>) {
        ${classNameLower}Dao.deleteById(<@generatePassingParameters table.pkColumns/>);
    }
    
	/** 
	 * 根据ID得到${className}
	 **/    
    public ${className} getById(<@generateArguments table.pkColumns/>) {
        return ${classNameLower}Dao.getById(<@generatePassingParameters table.pkColumns/>);
    }
    
	/** 
	 * 分页查询: ${className}
	 **/      
	@Transactional(readOnly=true)
	public Page<${className}> findPage(${className}Query query) {
	    Assert.notNull(query,"'query' must be not null");
		return ${classNameLower}Dao.findPage(query);
	}
	
<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Transactional(readOnly=true)
	public ${className} getBy${column.columnName}(${column.primitiveJavaType} ${column.columnNameFirstLower}) {
		return ${classNameLower}Dao.getBy${column.columnName}(${column.columnNameFirstLower});
	}	
	
	</#if>
</#list>
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(${className} ${classNameLower}) {
    }
    
    /**
     * ${className}的属性检查类,根据自己需要编写自定义检查
     **/
    public class ${className}Checker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdate${className}(${className} ${classNameLower}) {
            check${className}(${classNameLower});
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreate${className}(${className} ${classNameLower}) {
            check${className}(${classNameLower});
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void check${className}(${className} ${classNameLower}) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(${classNameLower});
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }
}
