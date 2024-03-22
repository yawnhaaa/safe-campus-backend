package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class CommentEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 资讯id
     */
    @TableField("info_id")
    private Long infoId;
    /**
     * 评论内容
     */
    @TableField("comment")
    private String comment;
    /**
     * 评论人id
     */
    @TableField("comment_id")
    private Long commentId;
    /**
     * 评论人昵称
     */
    @TableField("comment_name")
    private String commentName;
    /**
     * 被评论人id
     */
    @TableField("commented_id")
    private Long commentedId;
    /**
     * 被评论人昵称
     */
    @TableField("commented_name")
    private String commentedName;
    /**
     * 顶级评论id，没有就是顶级评论
     */
    @TableField("top_id")
    private Long topId;
    /**
     * 评论时间
     */
    @TableField("comment_time")
    private Date commentTime;
    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
