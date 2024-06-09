# 基于SpringBoot + Vue 的信息管理系统

## 本地快捷预览项目

第一步：运行 sql 文件夹下的`admin_info_1.sql`，创建`springboot-vue`数据库

第二步：双击 run 文件夹下的`start.cmd`，弹出的dos窗口不要关闭

第三步：浏览器访问`localhost:9090`，测试账号需要自己插入到数据库中

## 主要技术

SpringBoot、Mybatis-Plus、MySQL、Vue3、ElementPlus等

## 主要功能


## 主要功能截图

### 登录

登录支持两种用户角色：管理员和读者

输入正确的账号、密码后，系统会自动识别管理员或者用户，并跳转到相应主页。

### 展示板页面


## 代码结构

### 前端

```shell
library-ui
├─api			// api接口
├─assets		// 资源文件	
│  ├─icon	 	// 图标
│  ├─img	 	// 图片
│  └─styles	 	// 样式
├─components	// 自定义Vue组件
├─layout		// 页面布局
├─router		// Vue路由
├─utils			// 工具函数
└─views			// 页面
```


### 后端

maven项目结构

```shell
library-serve
├─java
│  └─com
│      └─admin
│          └─library
│              ├─common			// 通用类
│              │  ├─base		// 基础类
│              │  └─config		// 配置类
│              ├─controller		// 控制层
│              ├─domain			// 实体类
│              ├─mapper			// 持久层
│              └─service		// 业务层
└─resources	// maven资源配置
```

## 数据库

