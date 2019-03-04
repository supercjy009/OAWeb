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
    │	│   └── com.interest 
    │	│	├── controller  controller类
    │	│	├── dao  dao类
    │	│	├── model  entity类
    │	│	├── oauth2  spring security oauth2配置类
    │	│	├── properties  项目配置类
    │	│	├── security  spring security配置类
    │	│	├── service  service类
    │	│	└── utils  工具类
    │	└── resource  
    │	    ├── createTable 表数结构及表数据
    │	    ├── mybatis.mapper mybatis的mapper.xml
    │	    └── application.yml 项目配置文件
    └── test  测试
```

## 技术栈

 - spring boot
 - mybatis
 - Shiro
 - Redis
 - layui

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
 
