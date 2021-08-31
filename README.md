## 1. springboot-jdbc

## 2. springboot-druid

## 3. springboot-mybatis

### 坑
a. application中配置classpath时，path开头不加"/"代表resources目录下，加上"/"代表项目根目录下
b. 同时由application.properties和yml时，优先加载properties中的配置
c. 找不到mapper.xml时（Invalid bound statement (not found)），应按如下顺序排错：
    1). 检查mapper.xml文件中的头文件，以及namespace是否和mapper接口对应
    2). 检查query 的id是否和mapper接口中的方法名对应
    3). 检查application.properties和yml中配置的mapper-locations是否正确
    4). 检查一下target下是否生成了所有文件（特别是mapper.xml）

## 4. springboot-swagger

## 5. springboot-security

## 6. springboot-shiro02
