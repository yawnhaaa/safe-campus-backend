package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("question_content")
public class QuestionContentEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 外键
     */
    @TableField("question_id")
    private Long questionId;
    /**
     * 选项内容
     */
    @TableField("content")
    private String content;
    /**
     * 是否选择
     */
    @TableField("is_checked")
    private Boolean isChecked;
    /**
     * 是否正确
     */
    @TableField("is_true")
    private Boolean isTrue;
    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
