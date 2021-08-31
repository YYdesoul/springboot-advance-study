## I. 简介

Spring Boot是由Pivotal团队提供的全新框架，其**设计目的是用来简化新Spring应用的初始搭建以及开发过程**。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。

随着 Spring 不断的发展，涉及的领域越来越多，项目整合开发需要配合各种各样的文件，慢慢变得不那 么易用简单，违背了最初的理念，甚至人称配置地狱。Spring Boot 正是在这样的一个背景下被抽象出 来的开发框架，目的为了让大家更容易的使用 Spring 、更容易的集成各种常用的中间件、开源软件。

Spring Boot 基于 Spring 开发，Spirng Boot 本身并不提供 Spring 框架的核心特性以及扩展功能，只是 用于快速、敏捷地开发新一代基于 Spring 框架的应用程序。也就是说，它并不是用来替代 Spring 的解 决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具。Spring Boot 以**约定大于配 置的核心思想**，默认帮我们进行了很多设置，多数 Spring Boot 应用只需要很少的 Spring 配置。同时它 集成了大量常用的第三方库配置(例如 Redis、MongoDB、Jpa、RabbitMQ、Quartz 等等)，Spring Boot 应用中这些第三方库几乎可以零配置的开箱即用。

简单来说就是SpringBoot其实不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合 了所有的jar包，spring boot整合了所有的框架 。

Spring Boot的主要优点有：

- 为所有Spring开发者更快的入门 
- **开箱即用**，提供各种默认配置来简化项目配置 
- 内嵌式容器简化Web项目 
- 没有冗余代码生成和XML配置的要求

## II. 原理

SpringBoot的启动流程如下图：

![springboot启动流程](/Users/yanyu/OneDrive/MacbookPro/狂神/Java/课件/25_28_SpringBoot/blog/springboot-img/springboot启动流程.png)

SpringBoot原理的核心是自动装配。而自动装配是主启动类来启动的。

自动配置真正实现是从classpath中搜寻所有的META-INF/spring.factories配置文件 ，并将其中对应的org.springframework.boot.autoconfigure. 包下的配置项，通过反射实例化为对应标注了 @Configuration的JavaConfig形式的IOC容器配置类 ， 然后将这些都汇总成为一个实例并加载到IOC容 器中。

**结论:**

1. SpringBoot在启动的时候从类路径下的META-INF/spring.factories中获取 EnableAutoConfiguration指定的值

2. 将这些值作为自动配置类导入容器 ， 自动配置类就生效 ， 帮我们进行自动配置工作

3. 整个J2EE的整体解决方案和自动配置都在springboot-autoconfigure的jar包中

4. 它会给容器中导入非常多的自动配置类 (xxxAutoConfiguration), 就是给容器中导入这个场景需

	要的所有组件，并配置好这些组件 

5. 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作


这个类主要做了以下四件事情:

1. 推断应用的类型是普通的项目还是Web项目 
2. 查找并加载所有可用初始化器 ， 设置到initializers属性中 
3. 找出所有的应用程序监听器，设置到listeners属性中 
4. 推断并设置main方法的定义类，找到运行的主类

**自动装配精髓**

1. SpringBoot启动会加载大量的自动配置类 
2. 我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中
3. 我们再来看这个自动配置类中到底配置了哪些组件 (只要我们要用的组件存在在其中，我们就不需要再手动配置了)
4. 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中 指定这些属性的值即可

**xxxxAutoConfigurartion**:**自动配置类** 给容器中添加组件 

**xxxxProperties:封装配置文件中相关属性**

## III. 使用

### 准备工作

1. 在idea中选择spring initalizr
2. 勾选初始化组件（一般Web为必选）
3. 可选：在resources中创建并编写application.yml

### 添加一个功能

步骤和SpringMVC几乎相同

1. 创建controller包，并在其之下一个Controller，并编写一个Mapping

	```java
	package com.soul.controller;
	
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	@RestController
	public class HelloController {
	
	    @GetMapping("/hello")
	    public String hello() {
	        return "Hello World";
	    }
	}
	```

	

2. run XXXApplication.java文件

由此可知，SpringBoot项目是多么简单。



## IV. SpringBoot项目结构

SpringBoot项目结构如下：

![springboot项目目录](/Users/yanyu/OneDrive/MacbookPro/狂神/Java/课件/25_28_SpringBoot/blog/springboot-img/springboot项目目录.png)

SpringApplication是SpringBoot项目的主启动类，这个类主要做了以下四件事情：

1. 推断应用的类型是普通的项目还是Web项目 
2. 查找并加载所有可用初始化器 ， 设置到initializers属性中 
3. 找出所有的应用程序监听器，设置到listeners属性中 
4. 推断并设置main方法的定义类，找到运行的主类

## IV. yaml语法

YAML是 "YAML Ain't a Markup Language" (YAML不是一种标记语言)的递归缩写。 在开发的这种语言时，YAML 的意思其实是:"Yet Another Markup Language"(仍是一种标记语言) **这种语言以数据做为中心，而不是以标记语言为重点!**

yaml语法要点：

1. 空格不能省略 
2. 以缩进来控制层级关系，只要是左边对齐的一列数据都是同一个层级的
3. 属性和值的大小写都是十分敏感的

## V. SpringData

对于数据访问层，无论是 SQL(关系型数据库) 还是 NOSQL(非关系型数据库)，Spring Boot 底层都是采用 Spring Data 的方式进行统一处理。
Spring Boot 底层都是采用 Spring Data 的方式进行统一处理各种数据库，Spring Data 也是 Spring 中与 Spring Boot、Spring Cloud 等齐名的知名项目。

Sping Data 官网：https://spring.io/projects/spring-data

### 1. 集成JDBC

步骤如下：

a. 新建项目，记得引入Spring Web、JDBC API和MySQL Driver

b. 导入包（实际上已经自动导入了）：

```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<scope>runtime</scope>
</dependency>
```

c. 编写yaml配置数据文件连接数据库：

```yml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
```

c. 编写测试类测试连接：

```java
@SpringBootTest
class SpringbootDataJdbcApplicationTests {
    //DI注入数据源
    @Autowired
    DataSource dataSource;
    @Test
    public void contextLoads() throws SQLException {
    //看一下默认数据源
    System.out.println(dataSource.getClass());
    //获得连接
    Connection connection = dataSource.getConnection();
    System.out.println(connection);
    //关闭连接
    connection.close();
    }
}
```

结果：我们可以看到他默认给我们配置的数据源为 : class com.zaxxer.hikari.HikariDataSource ， 我们并没有手动配置

**HikariDataSource 号称 Java WEB 当前速度最快的数据源，相比于传统的 C3P0 、DBCP、Tomcat
jdbc 等连接池更加优秀**

d. 编写jdbc Controller:

```java
package com.soul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String, Object>> getUserList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public int addUser() {
        String sql = "insert into user(id, name, pwd) VALUES (10, '小明', '123456')";
        int update = jdbcTemplate.update(sql);
        return update;
    }

    @GetMapping("/deleteUser/{id}")
    public int deleteUser(@PathVariable int id) {
        String sql = "delete from user where id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    @GetMapping("/updateUser/{id}")
    public int updateUser(@PathVariable int id) {
        String sql = "update user set name = ?, pwd = ? where id = " + id;
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "123";
        int update = jdbcTemplate.update(sql, objects);
        return update;
    }
}
```



### 2. 集成Druid

Java程序很大一部分要操作数据库，为了提高性能操作数据库的时候，又不得不使用数据库连接池。Druid 是阿里巴巴开源平台上一个数据库连接池实现，结合了 C3P0、DBCP 等 DB 池的优点，同时加入了日志监控。

- Druid 可以很好的监控 DB 池连接和 SQL 的执行情况，天生就是针对监控而生的 DB 连接池。

- Druid已经在阿里巴巴部署了超过600个应用，经过一年多生产环境大规模部署的严苛考验。

Spring Boot 2.0 以上默认使用 Hikari 数据源，可以说 Hikari 与 Driud 都是当前 Java Web 上最优秀的数据源，我们来重点介绍 Spring Boot 如何集成 Druid 数据源，如何实现数据库监控。

Github地址：https://github.com/alibaba/druid/

**步骤入下：**

a. 添加Druid数据源依赖到pom.xml:

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
<groupId>com.alibaba</groupId>
<artifactId>druid</artifactId>
<version>1.1.21</version>
</dependency>
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
<groupId>log4j</groupId>
<artifactId>log4j</artifactId>
<version>1.2.17</version>
</dependency>
```

b. 编写application.yml配置文件配置数据库和Druid:

```yml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource # 自定义数据源

    #Spring Boot 默认是不注入这些属性值的，需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错 java.lang.ClassNotFoundException:org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

c. 现在需要程序员自己为 DruidDataSource 绑定全局配置文件中的参数，再添加到容器中，而不再使用 Spring Boot 的自动生成了；我们需要 自己添加 DruidDataSource 组件到容器中，并绑定属性

```java
package com.soul.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    /*
    将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
    绑定全局配置文件中的 druid 数据源属性到
    com.alibaba.druid.pool.DruidDataSource从而让它们生效
    @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局
    配置文件中
    前缀为 spring.datasource的属性值注入到
    com.alibaba.druid.pool.DruidDataSource 的同名参数中
    */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
```

d. 进行测试：

```java
package com.soul;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootDruidApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" +
                druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" +
                druidDataSource.getInitialSize());
        //关闭连接
        connection.close();
    }
}
```



### 3. 集成MyBatis

官方文档：http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
Maven仓库地址：https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter/2.1.1

**步骤入下：**

a. 导入MyBatis依赖到pom.xml：

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>
```

b. 配置application.yml

```yaml
spring:
  datasource:
    username: root
    password: 123456
    #?serverTimezone=UTC解决时区的报错
    url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver


mybatis:
  # config-location: classpath:mybatis/mybatis-config.xml # 用于配置核心配置文件，如果没有就不需要配置
  mapper-locations: classpath:mybatis/mapper/*.xml # 配置mapper的位置，相当于以前Mybatis中的的mappers(在resources下的位置)
  type-aliases-package: com.soul.pojo # 配置别名
```

c. 编写pojo层的实体类：

```java
package com.soul.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    int id;
    String name;
    String pwd;


}
```

d. 编写dao层（mapper层）接口：

```java
package com.soul.mapper;

import com.soul.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 方式一：表示是Mybatis的mapper类，方式二在启动类
@Repository // Dao层，托管到SpringBoot
public interface UserMapper {

    public List<User> getAllUsers();
}
```

e. 编写resources/mybatis/mapper下的UserMapper.xml：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.soul.mapper.UserMapper">
    <select id="getAllUsers" resultType="User">
        select * from user;
    </select>
</mapper>
```

f. 进行测试：

```java
package com.soul;

import com.soul.mapper.UserMapper;
import com.soul.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testMyBatis() {
        List<User> users = userMapper.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}

```

### 4. 集成Elastic Search

## VI. SpringBoot进阶

### 1. 集成Swagger

**前后端分离**
- 前端 -> 前端控制层、视图层
- 后端 -> 后端控制层、服务层、数据访问层
- 前后端通过API进行交互
- 前后端相对独立且松耦合

**产生的问题**
- 前后端集成，前端或者后端无法做到“及时协商，尽早解决”，最终导致问题集中爆发

**解决方案**

首先定义schema [ 计划的提纲 ]，并实时跟踪最新的API，降低集成风险

**Swagger**

- 号称世界上最流行的API框架
- Restful Api 文档在线自动生成器 => API 文档 与API 定义同步更新
- 直接运行，在线测试API
- 支持多种语言 （如：Java，PHP等）
- 官网：https://swagger.io/

**步骤入下**：

a. 新建一个SpringBoot-web项目

b. 在pom.xml中添加swagger依赖：

```xml
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swaggerui-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

c. 编写swagger配置类：

```java
package com.soul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2 // 开启swagger2
public class SwaggerConfig {
    // Config bean
    @Bean
    public Docket docket() {

        // 根据环境来开启swagger
        boolean flag = true;

        return new Docket(DocumentationType.SWAGGER_2)
                // 使用自己的全局状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
//                .useDefaultResponseMessages(true)
                .apiInfo(ApiInfo())
                .groupName("soul")  // 设置包名
                .enable(flag) // 设置什么环境下开启swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soul"))   // 设置扫描包
                .build();
    }

    private ApiInfo ApiInfo() {
        Contact contact = new Contact("Yu Yan", "", "yuyan@qq.com");

        return new ApiInfo("My Api Documentation",
                "My Api",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
```

d. 编写实体类：

```java
package com.soul.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel   // 设置为swagger Model
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty("ID号")    // 设置变量描述
    int id;
    @ApiModelProperty("名字")    // 设置变量描述
    String name;
}
```

e. 编写Controller:

```java
package com.soul.controller;

import com.soul.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @ApiOperation(value = "方法名", notes = "方法描述")    // 方法描述
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "id", paramType = "query", required = true, value = "id"),
            @ApiImplicitParam(dataType = "int", name = "name", paramType = "query", required = true, value = "名字")
    })
    @ApiResponses({  // 设置状态码
            @ApiResponse(code = 1000,
                        message = "状态码描述",
                        response = User.class       // 反射返回类型
                        )
    })
    @GetMapping("/hello/{id}/{name}")
    public User hello(@ApiParam(required = true) @PathVariable int id,    // @ApiParam设置swagger，如果设置了ApiImplicitParam,这里的注释会被覆盖
                       @ApiParam(value = "名字") @PathVariable String name) { // value: 设置描述
        User user = new User(id, name);
        return user;
    }
}

```

f. 运行项目，并访问http://localhost:8080/swagger-ui.html。（Note: 界面中可以找到api-docs json格式的访问地址）

### 2. 集成Redis

## VII. Springboot Security



## VIII. Springboot 分布式

















