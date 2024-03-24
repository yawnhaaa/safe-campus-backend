package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("question")
public class QuestionEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 题目类型
     */
    @TableField("type")
    private Integer type;
    /**
     * 题目内容
     */
    @TableField("title")
    private String title;
    /**
     * 解析
     */
    @TableField("analysis")
    private String analysis;
    /**
     * 是否提交
     */
    @TableField("is_submit")
    private Boolean isSubmit;
    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
