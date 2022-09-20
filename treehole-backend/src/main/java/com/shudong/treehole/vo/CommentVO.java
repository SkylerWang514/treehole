package com.shudong.treehole.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @program: treehole
 * @description: 评论VO
 * @author: 王珺玉
 * @create: 2021-07-16 16:47
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    @TableField("uid")
    private Long uid;

    @TableField("tid")
    private Long tid;

    @TableField("cContent")
    private String cContent;

    @TableField("cTime")
    @JsonFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime cTime;

    @TableField("anonymousOrNot")
    private Boolean anonymousOrNot;
}
