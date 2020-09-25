## 药品柜后台记录

1. api文档生成：
<br>(1) src/test/.../TestSwaggerStaticDoc，生成docs目录下的api文档。
<br>(2) pom.xml下的knife4j-spring-ui文件，生成api文档,访问/doc.html访问下载。
<br>(3) swagger 插件生成api测试平台。
2. slf4j日志使用
<br>(1) 参考pom.xml slf4j写法以及resources下的配置文件写法，添加logback-spring.xml以及application.properties的logging.config项，方可正常使用。
3. pom.xml 下的Tomcat.embed写法，保证springboot持续运行。
4. lombok + plugins插件，消除冗余代码。
5. hibernate-validator 完成数据常见的校验。
6. shiro配置完成用户登录状态以及权限管理的内容，参考https://www.jianshu.com/p/7f724bec3dc3
7. weblog api访问记录打印 参考https://www.cnblogs.com/Gent-Wang/p/11593316.html

readme edit 1+++++

