package com.shudong.treehole.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: treehole
 * @description: 获取点赞我的
 * @author: 曾薇
 * @create: 2022-06-08 09:30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThumbUpMeVO {
    private String uName;
    private String avatar;
    private String tContent;
}
