package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName("users")
public class UserEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 昵称
     */
    @TableField("name")
    private String name;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 密码
     */
    @TableField("passwd")
    private String passwd;
    /**
     * 盐
     */
    @TableField("salt")
    private String salt;
    /**
     * 个人介绍
     */
    @TableField("intro")
    private String intro;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;
    /**
     * 学校
     */
    @TableField("school")
    private String school;
    /**
     * 学院
     */
    @TableField("college")
    private String college;
    /**
     * 学号
     */
    @TableField("stu_num")
    private String stuNum;
    /**
     * 逻辑删除: 0 可用, 1 不可用
     */
    @TableField("is_delete")
    private Integer isDelete;
}
