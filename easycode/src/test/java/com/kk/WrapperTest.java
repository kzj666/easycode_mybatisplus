package com.kk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kk.dao.UserDao;
import com.kk.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/*
@author kzj
@date 2020/3/26 - 16:17
*/
@SpringBootTest
public class WrapperTest {
    @Autowired
    UserDao userDao;

    @Test
    void test1() {
        //selectList     查询name不为空的，邮箱不为空，年龄大于10岁的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userDao.selectList(wrapper);
    }

    @Test
    void test2() {
        //selectOne     查询单条记录，只能返回一条记录，否则报错
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", "tom");
        User user = userDao.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3() {
        //selectCount   查询记录数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .between("age", 5, 10);
        Integer count = userDao.selectCount(wrapper);
        System.out.println("数量:" + count);
    }

    @Test
    void test4() {
        //模糊查询，likeRight，表示%在右边，likeLeft表示%在左边
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                //名字不包含e的
                .notLike("name", "e")
                //email是t开头的
                .likeRight("email", "t");
        List<User> users = userDao.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test5(){
        //拼接sql
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 5");
        List<Map<String, Object>> maps = userDao.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test6(){
        //测试groupby
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.groupBy("name");
        List<Object> objs = userDao.selectObjs(wrapper);
        objs.forEach(System.out::println);
    }

    /**
     * setSqlSelect	设置 SELECT 查询字段
     * where	WHERE 语句，拼接 + WHERE 条件
     * and	AND 语句，拼接 + AND 字段=值
     * andNew	AND 语句，拼接 + AND (字段=值)
     * or	OR 语句，拼接 + OR 字段=值
     * orNew	OR 语句，拼接 + OR (字段=值)
     * eq	等于=
     * allEq	基于 map 内容等于=
     * ne	不等于<>
     * gt	大于>
     * ge	大于等于>=
     * lt	小于<
     * le	小于等于<=
     * like	模糊查询 LIKE
     * notLike	模糊查询 NOT LIKE
     * in	IN 查询
     * notIn	NOT IN 查询
     * isNull	NULL 值查询
     * isNotNull	IS NOT NULL
     * groupBy	分组 GROUP BY
     * having	HAVING 关键词
     * orderBy	排序 ORDER BY
     * orderAsc	ASC 排序 ORDER BY
     * orderDesc	DESC 排序 ORDER BY
     * exists	EXISTS 条件语句
     * notExists	NOT EXISTS 条件语句
     * between	BETWEEN 条件语句
     * notBetween	NOT BETWEEN 条件语句
     * addFilter	自由拼接 SQL
     * last	拼接在最后，例如：last("LIMIT 1")
     */

}
