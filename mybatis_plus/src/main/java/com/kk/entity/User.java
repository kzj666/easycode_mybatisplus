package com.kk.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-26 12:03:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -55928091333133859L;

    /**
     *
     *     AUTO(0), // 数据库id自增,此策略数据库的id必须是自增的
     *   NONE(1), // 未设置主键
     *   INPUT(2), // 手动输入。自己set
     *   ID_WORKER(3), // 默认的全局唯一id
     *   UUID(4), // 全局唯一id uuid
     *   ID_WORKER_STR(5); //ID_WORKER 字符串表示法
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //版本号
    @Version
    private Integer version;

    //逻辑删除
    @TableLogic
    private Integer deleted;

    //字段在规定条件下添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}