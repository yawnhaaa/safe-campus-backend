package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("info")
public class InfoEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 资讯标题
     */
    @TableField("title")
    private String title;
    /**
     * 资讯作者
     */
    @TableField("author")
    private String author;
    /**
     * 资讯作者id
     */
    @TableField("author_id")
    private Long authorId;
    /**
     * 资讯发布时间
     */
    @TableField("info_date")
    private Date infoDate;
    /**
     * 资讯内容
     */
    @TableField("content")
    private String content;
    /**
     * 资讯图片
     */
    @TableField("img")
    private String img;
    /**
     * 资讯点赞数
     */
    @TableField("like")
    private Integer like;
    /**
     * 资讯收藏数
     */
    @TableField("collect")
    private Integer collect;
    /**
     * 逻辑删除: 0 可用, 1 不可用
     */
    @TableField("is_delete")
    private Integer isDelete;
}
