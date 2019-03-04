## 项目介绍

spring boot项目，数据库mysql，shiro管理登录会话session
前端框架： layui
打包方式jar

项目目录结构
--
```shell
.
└── src
    ├── main  
    │	├── java  
    │	│	├── controller  controller类
    │	│	├── mapper  dao类
    │	│	├── model  entity类
    │	│	├── config  项目配置类
    │	│	├── service  service类
    │	│	└── util  工具类
    │	└── resource  
    │	    ├── mapping mybatis的mapper.xml
    │	    ├── templates 页面
    │	    └── application.yml 项目配置文件
    └── test  测试
```

## 技术栈

 - spring boot
 - mybatis
 - Shiro
 - Redis
 - thymeleaf、layui

## 接口设计：

	RESTful

## 数据库：

使用mysql。

**设计思路** 


## 项目搭建

**运行环境：**

  jdk1.8+maven。
  
 **数据库配置：**
 
 数据库mysql
 
 **缓存配置：** 
 
  配置redis，且redis服务必须开启。
 
