package com.kk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.entity.User;
import com.kk.mapper.UserMapper;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 测试插入，主键的自动生成策略，实体类中在id字段上添加@TableId注解，配置主键生成策略
     */
    @Test
    void insert(){
        User user = new User();
        user.setName("jj");
        user.setAge(18);
        user.setEmail("2319680237@qq.com");
        userMapper.insert(user);
        //发现id会根据配置的策略自动生成
    }

    /**
     * 测试更新，同时实现createTime和updateTime的自动生成，通过在实体类字段添加@TableField注解，添加一个MyMetaHandler插件来实现
     */
    @Test
    void update(){
        User user = new User();
        user.setId(8L);
        user.setName("kk");
        user.setAge(8);
        user.setEmail("2319680237@qq.com");
        userMapper.updateById(user);
    }

    /**
     * 乐观锁，一般会使用版本号机制或CAS操作实现。
     *
     * CAS操作方式：即compare and swap 或者 compare and set，涉及到三个操作数，数据所在的内存值，预期值，新值。
     * 当需要更新时，判断当前内存值与之前取到的值是否相等。
     * 若相等，则用新值更新。
     * 若失败则重试，一般情况下是一个自旋操作，即不断的重试。
     *
     * 测试乐观锁，在实体类的version字段上加@Version注解，再添加一个MyBatisPlus的配置类实现。
     * 乐观锁在操作时候会带上version字段作为条件，要更新数据值时，在读取数据的同时也会读取version值，
     * 在提交更新时，若刚才读取到的version值为当前数据库中的version值相等时才更新，否则重试更新操作，直到更新成功。
     */
    @Test
    void testOptimisticLock(){

        //线程一,更新
        User user = userMapper.selectById(8L);
        user.setName("彭于晏");

        //线程二，插队并完成更新
        var user1 = userMapper.selectById(8L);
        user1.setName("梁朝伟");
        userMapper.updateById(user1);

        //线程一，继续更新
        userMapper.updateById(user);
    }


    /**
     *  测试批量查询！
     */
    @Test
    void testSelectByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 按条件查询之一，可使用map操作
     */
    @Test
    void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询（且条件）
        map.put("name","kk");
        map.put("age",8);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 测试分页，在配置类中配置分页拦截器插件即可
     */
    @Test
    void testPage(){
        //查询第一页，五条数据
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println("总记录数："+page.getTotal());
    }

    /**
     * 测试删除
     */
    @Test
    void testDelete(){
        userMapper.deleteById(1243056477648744449L);
//        列表删除
//        userMapper.deleteBatchIds(Arrays.asList(1,2,3));

//        多条件删除
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name","james");
//        userMapper.deleteByMap(map);

    }
    /**
     * 测试逻辑删除
     * 在数据库中添加deleted字段，默认值为0。实体类同步字段，添加@TableLogic注解，在配置类中添加逻辑删除插件，再在配置文件中配置逻辑删除的属性
     */
    @Test
    void testlogic(){
        //观察日志，发现实际上走的是更新操作，且数据库中该用户的deleted值变为了1，数据库中的记录依然存在
        userMapper.deleteById(1L);
        //看看能不能查出来(观察日志，发现查询时自动带上了deleted字段，结果是查不出来)
        userMapper.selectById(1L);
    }

    /**
     * SQL效率执行插件
     * 导入SQL执行效率插件，配置超时时间，设置格式化代码为true
     * 记住，要在SpringBoot中配置环境为 dev 或者 test 环境！
     *
     */

}

