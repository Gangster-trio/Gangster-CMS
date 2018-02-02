-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: db_cms
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `db_cms`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db_cms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_cms`;

--
-- Table structure for table `cms_article`
--

DROP TABLE IF EXISTS `cms_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_article` (
  `article_id` int(20) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(255) DEFAULT NULL,
  `article_type` varchar(255) DEFAULT NULL,
  `article_author` varchar(255) DEFAULT NULL,
  `article_url` varchar(255) DEFAULT NULL,
  `article_order` int(11) DEFAULT NULL,
  `article_site_id` int(11) DEFAULT NULL,
  `article_category_id` int(11) DEFAULT NULL,
  `article_create_time` datetime DEFAULT NULL,
  `article_update_time` datetime DEFAULT NULL,
  `article_thumb` varchar(255) DEFAULT NULL,
  `article_hit` int(11) DEFAULT NULL,
  `article_desc` varchar(255) DEFAULT NULL,
  `article_status` int(11) DEFAULT NULL,
  `article_content` longtext,
  `article_skin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `cms_article_cms_category_category_id_fk` (`article_category_id`),
  KEY `cms_article_cms_site_site_id_fk` (`article_site_id`),
  CONSTRAINT `cms_article_cms_category_category_id_fk` FOREIGN KEY (`article_category_id`) REFERENCES `cms_category` (`category_id`),
  CONSTRAINT `cms_article_cms_site_site_id_fk` FOREIGN KEY (`article_site_id`) REFERENCES `cms_site` (`site_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_article`
--

LOCK TABLES `cms_article` WRITE;
/*!40000 ALTER TABLE `cms_article` DISABLE KEYS */;
INSERT INTO `cms_article` VALUES (84,'Spring 教程','normal','me',NULL,NULL,NULL,12,'2018-02-02 10:59:13',NULL,NULL,NULL,NULL,NULL,'<pre><div><div class=\"article\"><div data-note-content=\"\" class=\"show-content\"><div class=\"show-content-free\"><div class=\"image-package\"></div>\n<p>Spring 框架是一个开源的 Java 平台，它为容易而快速的开发出耐用的 Java 应用程序提供了全面的基础设施。</p>\n<p>Spring 框架最初是由 Rod Johnson 编写的，并且 2003 年 6 月首次在 Apache 2.0 许可下发布。</p>\n<p>本教程是基于在 2015 年 3 月发布的 Spring 框架 4.1.6 版本编写的。</p>\n<h2>适用人群</h2>\n<p>本教程是为需要详细了解 Spring 框架的体系结构和实际应用的 Java 程序员设计的。本教程将带你达到中级的专业知识水平，而你可以将自己提升至更高层次的专业知识水平。</p>\n<h2>学习前提</h2>\n<p>在进行本教程之前，你应该对 Java 编程语言有一个很好的了解。对 Eclipse IDE 的基本了解也是必须的，因为所有的示例都是使用 Eclipse IDE 进行编译的。</p>\n<table class=\"layui-table\">\n<thead>\n<tr>\n<th>更新日期</th>\n<th>更新内容</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>2015-06-18</td>\n<td>Spring 教程</td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/overview.html\" target=\"_blank\" rel=\"nofollow\">概述</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/architecture.html\" target=\"_blank\" rel=\"nofollow\">体系结构</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/environment-setup.html\" target=\"_blank\" rel=\"nofollow\">环境配置</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/hello-world-example.html\" target=\"_blank\" rel=\"nofollow\">Hello World 实例</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-containers.html\" target=\"_blank\" rel=\"nofollow\">IoC 容器</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-container/spring-bean-fatory-container.html\" target=\"_blank\" rel=\"nofollow\">Spring BeanFactory 容器</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-container/spring-application-context-container.html\" target=\"_blank\" rel=\"nofollow\">Spring ApplicationContext 容器</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-definition.html\" target=\"_blank\" rel=\"nofollow\">Bean 定义</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-scopes.html\" target=\"_blank\" rel=\"nofollow\">Bean 作用域</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-life-cycle.html\" target=\"_blank\" rel=\"nofollow\">Bean 生命周期</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-post-processors.html\" target=\"_blank\" rel=\"nofollow\">Bean 后置处理器</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-definition-inheritance.html\" target=\"_blank\" rel=\"nofollow\">Bean 定义继承</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">依赖注入</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection/spring-constructor-based-dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">Spring 基于构造函数的依赖注入</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection/spring-setter-based-dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">Spring 基于设值函数的依赖注入</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/injecting-inner-beans.html\" target=\"_blank\" rel=\"nofollow\">注入内部 Beans</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/injecting-collection.html\" target=\"_blank\" rel=\"nofollow\">注入集合</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-autowiring.html\" target=\"_blank\" rel=\"nofollow\">Beans 自动装配</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byname.html\" target=\"_blank\" rel=\"nofollow\">Spring 自动装配 ‘byName’</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byType.html\" target=\"_blank\" rel=\"nofollow\">Spring 自动装配 ‘byType’</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-by-Constructor.html\" target=\"_blank\" rel=\"nofollow\">Spring 由构造函数自动装配</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration.html\" target=\"_blank\" rel=\"nofollow\">基于注解的配置</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-required-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Required 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-autowired-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Autowired 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-qualifier-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Qualifier 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-jsr250-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring JSR-250 注释</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/java-based-configuration.html\" target=\"_blank\" rel=\"nofollow\">基于 Java 的配置</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/event-handling-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中的事件处理</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/custom-events-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中的自定义事件</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 框架的 AOP</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring-framenwork/xml-schema-based-aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中基于 AOP 的 XML架构</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring-framenwork/aspectj-based-aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中基于 AOP 的 @AspectJ</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework.html\" target=\"_blank\" rel=\"nofollow\">JDBC 框架</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework-overview/spring-jdbc-example.html\" target=\"_blank\" rel=\"nofollow\">Spring JDBC 示例</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework-overview/sql-stored-procedure-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中 SQL 的存储过程</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management.html\" target=\"_blank\" rel=\"nofollow\">事务管理</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management/spring-programmatic-transaction-management.html\" target=\"_blank\" rel=\"nofollow\">Spring 编程式事务管理</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management/spring-declarative-transaction-management.html\" target=\"_blank\" rel=\"nofollow\">Spring 声明式事务管理</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=web-mvc-framework.html\" target=\"_blank\" rel=\"nofollow\">Web MVC 框架</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-mvc-hello-world-example.html\" target=\"_blank\" rel=\"nofollow\">Spring MVC Hello World 例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-mvc-form-handling-example.html\" target=\"_blank\" rel=\"nofollow\">Spring MVC 表单处理例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-page-redirection-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 页面重定向例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-static-pages-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 静态页面例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-exception-handling-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 异常处理例子</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/logging-with-log4j.html\" target=\"_blank\" rel=\"nofollow\">使用 Log4J 记录日志</a></li>\n</ul>\n\n          </div>\n        </div>\n    </div>\n\n    <!-- 连载目录项 -->\n\n    <!-- 打赏文章、购买文章、购买连载 -->\n        <div id=\"free-reward-panel\" class=\"support-author\"><p></p></div></div><br><br>作者：极客学院Wiki<br>链接：https://www.jianshu.com/p/bd87d5507f5e<br>來源：简书<br>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</pre>','default'),(85,'这是一篇主页文章^_^','index','me',NULL,NULL,NULL,NULL,'2018-02-02 11:23:06',NULL,NULL,NULL,NULL,NULL,'<pre><div><li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/overview.html\" target=\"_blank\" rel=\"nofollow\">概述</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/architecture.html\" target=\"_blank\" rel=\"nofollow\">体系结构</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/environment-setup.html\" target=\"_blank\" rel=\"nofollow\">环境配置</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/hello-world-example.html\" target=\"_blank\" rel=\"nofollow\">Hello World 实例</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-containers.html\" target=\"_blank\" rel=\"nofollow\">IoC 容器</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-container/spring-bean-fatory-container.html\" target=\"_blank\" rel=\"nofollow\">Spring BeanFactory 容器</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/ioc-container/spring-application-context-container.html\" target=\"_blank\" rel=\"nofollow\">Spring ApplicationContext 容器</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-definition.html\" target=\"_blank\" rel=\"nofollow\">Bean 定义</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-scopes.html\" target=\"_blank\" rel=\"nofollow\">Bean 作用域</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-life-cycle.html\" target=\"_blank\" rel=\"nofollow\">Bean 生命周期</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-post-processors.html\" target=\"_blank\" rel=\"nofollow\">Bean 后置处理器</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/bean-definition-inheritance.html\" target=\"_blank\" rel=\"nofollow\">Bean 定义继承</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">依赖注入</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection/spring-constructor-based-dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">Spring 基于构造函数的依赖注入</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/dependency-injection/spring-setter-based-dependency-injection.html\" target=\"_blank\" rel=\"nofollow\">Spring 基于设值函数的依赖注入</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/injecting-inner-beans.html\" target=\"_blank\" rel=\"nofollow\">注入内部 Beans</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/injecting-collection.html\" target=\"_blank\" rel=\"nofollow\">注入集合</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-autowiring.html\" target=\"_blank\" rel=\"nofollow\">Beans 自动装配</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byname.html\" target=\"_blank\" rel=\"nofollow\">Spring 自动装配 ‘byName’</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-byType.html\" target=\"_blank\" rel=\"nofollow\">Spring 自动装配 ‘byType’</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/beans-auto-wiring/spring-autowiring-by-Constructor.html\" target=\"_blank\" rel=\"nofollow\">Spring 由构造函数自动装配</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration.html\" target=\"_blank\" rel=\"nofollow\">基于注解的配置</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-required-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Required 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-autowired-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Autowired 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-qualifier-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring @Qualifier 注释</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/annotation-based-configuration/spring-jsr250-annotation.html\" target=\"_blank\" rel=\"nofollow\">Spring JSR-250 注释</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/java-based-configuration.html\" target=\"_blank\" rel=\"nofollow\">基于 Java 的配置</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/event-handling-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中的事件处理</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/custom-events-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中的自定义事件</a></li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 框架的 AOP</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring-framenwork/xml-schema-based-aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中基于 AOP 的 XML架构</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/aop-with-spring-framenwork/aspectj-based-aop-with-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中基于 AOP 的 @AspectJ</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework.html\" target=\"_blank\" rel=\"nofollow\">JDBC 框架</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework-overview/spring-jdbc-example.html\" target=\"_blank\" rel=\"nofollow\">Spring JDBC 示例</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/jdbc-framework-overview/sql-stored-procedure-in-spring.html\" target=\"_blank\" rel=\"nofollow\">Spring 中 SQL 的存储过程</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management.html\" target=\"_blank\" rel=\"nofollow\">事务管理</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management/spring-programmatic-transaction-management.html\" target=\"_blank\" rel=\"nofollow\">Spring 编程式事务管理</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/transaction-management/spring-declarative-transaction-management.html\" target=\"_blank\" rel=\"nofollow\">Spring 声明式事务管理</a></li>\n</ul>\n</li>\n<li>\n<a href=\"https://link.jianshu.com?t=web-mvc-framework.html\" target=\"_blank\" rel=\"nofollow\">Web MVC 框架</a>\n<ul>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-mvc-hello-world-example.html\" target=\"_blank\" rel=\"nofollow\">Spring MVC Hello World 例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-mvc-form-handling-example.html\" target=\"_blank\" rel=\"nofollow\">Spring MVC 表单处理例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-page-redirection-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 页面重定向例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-static-pages-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 静态页面例子</a></li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/mvc-framework/spring-exception-handling-example.html\" target=\"_blank\" rel=\"nofollow\">Spring 异常处理例子</a></li>\n</ul>\n</li>\n<li><a href=\"https://link.jianshu.com?t=http://wiki.jikexueyuan.com/project/spring/logging-with-log4j.html\" target=\"_blank\" rel=\"nofollow\">使用 Log4J 记录日志</a></li></div><br><br>作者：极客学院Wiki<br>链接：https://www.jianshu.com/p/bd87d5507f5e<br>來源：简书<br>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</pre>','default');
/*!40000 ALTER TABLE `cms_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_category`
--

DROP TABLE IF EXISTS `cms_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_title` varchar(100) DEFAULT NULL,
  `category_create_time` datetime DEFAULT NULL,
  `category_update_time` datetime DEFAULT NULL,
  `category_parent_id` int(11) DEFAULT NULL,
  `category_level` int(11) DEFAULT NULL,
  `category_site_id` int(11) DEFAULT NULL,
  `category_status` int(11) DEFAULT NULL,
  `category_desc` varchar(255) DEFAULT NULL,
  `category_order` int(11) DEFAULT NULL,
  `category_skin` varchar(255) DEFAULT NULL,
  `category_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `cms_category_cms_category_category_id_fk` (`category_parent_id`),
  KEY `cms_category_cms_site_site_id_fk` (`category_site_id`),
  CONSTRAINT `cms_category_cms_category_category_id_fk` FOREIGN KEY (`category_parent_id`) REFERENCES `cms_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cms_category_cms_site_site_id_fk` FOREIGN KEY (`category_site_id`) REFERENCES `cms_site` (`site_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_category`
--

LOCK TABLES `cms_category` WRITE;
/*!40000 ALTER TABLE `cms_category` DISABLE KEYS */;
INSERT INTO `cms_category` VALUES (1,'root','2018-02-02 00:48:46','2018-02-02 00:48:49',NULL,-1,1,NULL,'root category',NULL,'default','normal'),(11,'programe','2018-02-02 10:40:05',NULL,1,0,1,NULL,'coding',NULL,'default','normal'),(12,'java','2018-02-02 10:40:29',NULL,11,1,1,NULL,'love',NULL,'default','normal'),(13,'python','2018-02-02 10:40:46',NULL,11,1,1,NULL,'simple',NULL,'default','normal'),(14,'life','2018-02-02 10:41:15',NULL,1,0,1,NULL,'..',NULL,'default','normal');
/*!40000 ALTER TABLE `cms_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_group`
--

DROP TABLE IF EXISTS `cms_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL,
  `group_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `cms_group_group_name_uindex` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_group`
--

LOCK TABLES `cms_group` WRITE;
/*!40000 ALTER TABLE `cms_group` DISABLE KEYS */;
INSERT INTO `cms_group` VALUES (8,'admin',NULL);
/*!40000 ALTER TABLE `cms_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_module`
--

DROP TABLE IF EXISTS `cms_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(100) DEFAULT NULL,
  `module_url` varchar(255) DEFAULT NULL,
  `module_parent_id` int(11) DEFAULT NULL,
  `module_sort` int(11) DEFAULT NULL,
  `module_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_module`
--

LOCK TABLES `cms_module` WRITE;
/*!40000 ALTER TABLE `cms_module` DISABLE KEYS */;
INSERT INTO `cms_module` VALUES (1,'content manage','',0,NULL,'2018-01-31 01:40:05'),(2,'article manage','/listArticle.html',1,NULL,'2018-01-31 01:40:42'),(3,'column manage','/listCategory.html',1,NULL,'2018-01-31 01:42:00'),(4,'permission manage',NULL,0,NULL,'2018-01-31 01:42:26'),(5,'admin manage','index.html',4,NULL,'2018-01-31 01:43:11'),(6,'group manage','',4,NULL,NULL);
/*!40000 ALTER TABLE `cms_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_permission`
--

DROP TABLE IF EXISTS `cms_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_permission`
--

LOCK TABLES `cms_permission` WRITE;
/*!40000 ALTER TABLE `cms_permission` DISABLE KEYS */;
INSERT INTO `cms_permission` VALUES (1,'1',NULL);
/*!40000 ALTER TABLE `cms_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_site`
--

DROP TABLE IF EXISTS `cms_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_site` (
  `site_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_name` varchar(100) DEFAULT NULL,
  `site_url` varchar(100) DEFAULT NULL,
  `site_desc` varchar(255) DEFAULT NULL,
  `site_copyright` varchar(100) DEFAULT NULL,
  `site_skin` varchar(255) DEFAULT NULL,
  `site_create_time` datetime DEFAULT NULL,
  `site_status` int(11) DEFAULT NULL,
  `site_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`site_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_site`
--

LOCK TABLES `cms_site` WRITE;
/*!40000 ALTER TABLE `cms_site` DISABLE KEYS */;
INSERT INTO `cms_site` VALUES (1,'first site','index',NULL,NULL,'default',NULL,NULL,NULL);
/*!40000 ALTER TABLE `cms_site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_skin`
--

DROP TABLE IF EXISTS `cms_skin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_skin` (
  `skin_name` varchar(100) NOT NULL,
  `skin_create_time` datetime DEFAULT NULL,
  `skin_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`skin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_skin`
--

LOCK TABLES `cms_skin` WRITE;
/*!40000 ALTER TABLE `cms_skin` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_skin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_sys`
--

DROP TABLE IF EXISTS `cms_sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_sys` (
  `sys_key` varchar(255) NOT NULL,
  `sys_value` varchar(255) DEFAULT NULL,
  `sys_create_time` datetime DEFAULT NULL,
  `sys_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_sys`
--

LOCK TABLES `cms_sys` WRITE;
/*!40000 ALTER TABLE `cms_sys` DISABLE KEYS */;
INSERT INTO `cms_sys` VALUES ('pic_path','/home/bigmeng/Pictures/',NULL,NULL);
/*!40000 ALTER TABLE `cms_sys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_tag`
--

DROP TABLE IF EXISTS `cms_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  `tag_create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `cms_tag_tag_name_uindex` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_tag`
--

LOCK TABLES `cms_tag` WRITE;
/*!40000 ALTER TABLE `cms_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_user`
--

DROP TABLE IF EXISTS `cms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_password` varchar(35) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_create_time` datetime DEFAULT NULL,
  `user_last_login` datetime DEFAULT NULL,
  `user_org` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_user`
--

LOCK TABLES `cms_user` WRITE;
/*!40000 ALTER TABLE `cms_user` DISABLE KEYS */;
INSERT INTO `cms_user` VALUES (1,'admin',NULL,'root',NULL,NULL,'2018-01-31 05:50:14',NULL,NULL);
/*!40000 ALTER TABLE `cms_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_permission`
--

DROP TABLE IF EXISTS `group_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_permission` (
  `group_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  KEY `group_permission_cms_group_group_id_fk` (`group_id`),
  KEY `group_permission_cms_permission_permission_id_fk` (`permission_id`),
  CONSTRAINT `group_permission_cms_group_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `cms_group` (`group_id`),
  CONSTRAINT `group_permission_cms_permission_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `cms_permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_permission`
--

LOCK TABLES `group_permission` WRITE;
/*!40000 ALTER TABLE `group_permission` DISABLE KEYS */;
INSERT INTO `group_permission` VALUES (8,1);
/*!40000 ALTER TABLE `group_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_article`
--

DROP TABLE IF EXISTS `tag_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag_article` (
  `tag_id` int(11) DEFAULT NULL,
  `article_id` int(20) DEFAULT NULL,
  KEY `tag_article_cms_article_article_id_fk` (`article_id`),
  KEY `tag_article_cms_tag_tag_id_fk` (`tag_id`),
  CONSTRAINT `tag_article_cms_article_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`article_id`),
  CONSTRAINT `tag_article_cms_tag_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `cms_tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_article`
--

LOCK TABLES `tag_article` WRITE;
/*!40000 ALTER TABLE `tag_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  KEY `user_group_cms_group_group_id_fk` (`group_id`),
  KEY `user_group_cms_user_user_id_fk` (`user_id`),
  CONSTRAINT `user_group_cms_group_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `cms_group` (`group_id`),
  CONSTRAINT `user_group_cms_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `cms_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,8);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-02 19:24:59
