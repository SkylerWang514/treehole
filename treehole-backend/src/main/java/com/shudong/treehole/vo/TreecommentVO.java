package com.shudong.treehole.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * @program: treehole
 * @description: 获取某树洞的评论时返回给前端的json格式
 * @author: 乔鑫龙
 * @create: 2021-06-02 10:04
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreecommentVO {
    private String uName;
    private String cContent;
    private String avatar;

    @JsonFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime cTime;
    private Boolean anonymousOrNot;
}
