package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("police")
public class PoliceEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("long_la")
    private String longLa;
    @TableField("name")
    private String name;
    @TableField("address")
    private String address;
    @TableField("intro")
    private String intro;
    @TableField("tel")
    private String tel;
    @TableField("is_delete")
    private Integer isDelete;
}
