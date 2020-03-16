# InfectStatisticWeb
结对第二次作业——某次疫情统计可视化的实现
### [作业链接](https://edu.cnblogs.com/campus/fzu/2020SPRINGS/homework/10460)
### 结对学号：
* 221701208     叶尤澎
* 221701225     陈启元

### 项目介绍:
* 本项目为采用springboot框架开发的web项目，使用Maven管理项目。
* 程序可实现按日期可视化显示全国疫情数据，用户可选择日期查询某一天的数据并获得数据可视化的体验。
* 程序实现按日期查询并将省份疫情数据可视化，还有今日与昨日相比较数据的增减。
* 本项目实现了疫情讯息文章的展示/增添/删除功能。
*  **由于时间问题**，没有实现进入后台所需的管理员登陆功能。暂时实现为在全国地图页面顶部直接提供后台的访问链接。

### 项目构建
本项目使用了springboot框架，使用maven管理项目依赖；下载源码后，使用maven（开发时使用的是IDEA内置的maven）导入好项目所需的依赖即可运行。

### 项目运行
运行后会运行一个地址为localhost，在8080端口接收请求的tomcat服务器。之后在浏览器端输入localhost:8080/country即可进入到全国疫情地图界面，
开始使用。
附：页面的路径：
- 全国地图：localhost:8080/country
- 省份：localhost:8080/province?provinceName=xxx
- 后台疫情快讯相关：
  - 列表：localhost:8080/articles
  - 新增文章：localhost:8080/article/add
