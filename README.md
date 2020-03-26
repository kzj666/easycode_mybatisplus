# EasyCode结合MyBatisPlus

因为不习惯MyBatisPlus代码生成器生成的代码结构，EasyCode自动生成的代码结构比较熟悉舒服，所以结合EasyCode和MyBatisPlus一起使用。


>  [EasyCode](https://mp.weixin.qq.com/s/shZSdXq8AzTaO04Z0ZAF5w)
> 
> [MyBatisPlus官网](https://baomidou.oschina.io/mybatis-plus-doc/#/)


### 其实很简单，先导入依赖

```xml
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!--lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```
   
### 利用EasyCode生成代码，在UserDao接口上加上@Repository注解，然后让UserDao接口继承extends BaseMapper<User>

然后userDao就可以利用MyBatisPlus的方法了


MyBatisPlus的常用方法实例在test包下