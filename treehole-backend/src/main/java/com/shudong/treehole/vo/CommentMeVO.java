package com.shudong.treehole.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shudong.treehole.entity.Treehole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @program: treehole
 * @description: 获取评论我的时返回给前端的json格式
 * @author: 曾薇
 * @create: 2022-06-07 10:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentMeVO implements Comparable<CommentMeVO>{
    private Long uid;
    private String uName;
    private String cContent;
    private String avatar;

    @JsonFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime cTime;
    private Boolean anonymousOrNot;

    private TreeholeVO treeholeVO;


    @Override
    public int compareTo(CommentMeVO commentMeVO) {
        return (int) (commentMeVO.getCTime().toInstant(ZoneOffset.of("+8")).toEpochMilli() - this.getCTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
