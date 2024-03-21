package com.safe.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("info_user")
public class InfoUserEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 资讯id
     */
    @TableField("info_id")
    private Long infoId;
    /**
     * 复合id
     */
    @TableField("user_info_id")
    private Long userInfoId;
    /**
     * 是否喜欢
     */
    @TableField("is_like")
    private Integer isLike;
    /**
     * 喜欢时间
     */
    @TableField("like_time")
    private Date likeTime;
    /**
     * 是否收藏
     */
    @TableField("is_collect")
    private Integer isCollect;
    /**
     * 收藏时间
     */
    @TableField("collect_time")
    private Date collectTime;
}
