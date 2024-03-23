package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("material")
public class MaterialEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 素材路径
     */
    @TableField("src")
    private String src;
    /**
     * 展示图片路径
     */
    @TableField("img_src")
    private String imgSrc;
    /**
     * 素材标题
     */
    @TableField("title")
    private String title;
    /**
     * 素材作者
     */
    @TableField("author")
    private String author;
    /**
     * 作者id
     */
    @TableField("author_id")
    private String authorId;
    /**
     * 发布日期
     */
    @TableField("material_date")
    private Date materialDate;
    /**
     * 素材类型
     */
    @TableField("material_type")
    private String materialType;
    /**
     * 下载次数
     */
    @TableField("download")
    private String download;
    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private String isDelete;
}
