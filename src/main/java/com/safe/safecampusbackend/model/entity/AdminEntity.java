package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class AdminEntity {
    @TableId(value = "id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("passwd")
    private String passwd;
    @TableField("salt")
    private String salt;
}
