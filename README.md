#声明

此项目使用和修改了部分[MyBatis-plus](https://github.com/baomidou/mybatis-plus)代码.

#功能简介

用于对MyBatis3.x物理分页的简单支持

#使用
##依赖
依赖于MyBatis3.x
##配置
- MyBatis插件配置
```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <plugins>
        <plugin interceptor="com.tingfeng.java.mybatis.plugins.PaginationInterceptor"/>
    </plugins>
</configuration>
```
- MyBatis的自带的rowbounds分页
我们可以用MyBatis-Generator生辰自带分页信息的example代码.
需要的可以在MyBatis-Generator的配置文件中加入此配置.
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" > 
<generatorConfiguration >
  <context id="jqtek" >
   <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin">
</plugin> 
  </context>
</generatorConfiguration>
```
##使用
- 使用官方自带的rowbounds
```java
public void testSelecttUserInfo() throws Exception{    
        TUserExample example = new  TUserExample();
        TUserExample.Criteria critera = example.createCriteria() ;
        RowBounds rowBounds = new RowBounds(1,1);
        List<TUser> users = tUserMapper.selectByExampleWithRowbounds(example,rowBounds);
        System.out.println(users.size());
    }
```
插件将会自动将内存分页信息转换为物理分页信息.
- 自写mapper使用分页
Mapper示例:
```java
import com.tingfeng.java.mybatis.plugins.PaginationI;
public interface TUserExtendMapper {
    public List<TUser> finUser(@Param("group")String group,@Param("page")PaginationI page);
}
```
**说明:**
    如果你需要在一个查询方法中加入自动分页的功能,参数的类型必须是map.
    可以传入@Param("page")PaginationI page ,
    具体的参数实现类必须实现接口com.tingfeng.java.mybatis.plugins.PaginationI,
  当然你也可以自己实现此接口并传入,但是
    **参数名称必须是 "page"**
Mapper的xml文件:
**注意:**
参数的类型必须是map
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.dao.extend.TUserExtendMapper">
  <select id="finUser" parameterType="map" resultMap="com.TUser">
    select * from t_user where user_group like CONCAT('%',CONCAT(#{group},'%'))
  </select>
</mapper>
```
查询示例:
**注意:**
这里的Pagination实现了接口com.tingfeng.java.mybatis.plugins.PaginationI,是系统提供的默认实现类.
```java
import com.tingfeng.java.mybatis.plugins.Pagination;
public void testSelecttUserInfo2() throws Exception{
        List<TUser> users =tUserExtendMapper.finUser("a", new Pagination(10,1));
        System.out.println(users.size());
    }
```
#支持
支持常见的数据库有:mysql;oracle;postgre;sqlite;sqlserver;h2;hsql