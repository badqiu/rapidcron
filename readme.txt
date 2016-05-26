----------------------------------------
基本目录说明
----------------------------------------
config		配置目录，存放所有的配置文件,该目录可以作为 classpath 目录在应用中引用
doc			文档目录，存放文档
shell		脚本目录，存放如linux脚本

----------------------------------------
项目目录说明
----------------------------------------
util		工具类项目
model		dao数据模型,对应数据库表的model代码
dao			dao项目,存放数据持久层代码
service		service层项目, 存放普通服务
core-service 存放核心服务, 目的： 如项目开发过程中，有部分service是很重要的，可以给其它service重用，则创建该项目，普通的service重用度不高的，不要放在该项目
scheduler	定时调度任务项目, 包含所有定时调度任务,目的: 调度任务一般不需要多机部署，只要跟后台任务一样，部署单实例即可
web-home	web前台项目		目的: 将前台项目与后台部署分离，进程分离，1. 避免后台更新，前台需要重启 2. 后台不需要那么多实例 3.后台的权限体系与前台不一样
web-admin	web后台项目
webservice-api  	用于对外发布webservice给外部系统调用，包含webservice的 接口，异常，DTO等类；
webservice-server	用于存放webservice-api项目的server端实现类(impl类)
webservice-client 	webservice-api的客户端实现类，如果直接暴露webservice-api给客户端，导致api太难使用,或需要增加数据缓存，可以通过本项目再包装一层

----------------------------------------
项目依赖结构
----------------------------------------
	util
	model
		dao
			core-service
				service
					web-home
					web-admin
					scheduler
					webservice-server
	webservice-api
		webservice-server
		webservice-client

		