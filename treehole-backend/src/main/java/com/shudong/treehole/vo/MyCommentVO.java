package com.shudong.treehole.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @program: treehole
 * @description: 获取我的评论时返回给前端的json格式
 * @author: 曾薇
 * @create: 2022-06-08 09:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCommentVO implements Comparable<MyCommentVO>{
    private String cContent;

    @JsonFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime cTime;

    private TreeholeVO treeholeVO;


    @Override
    public int compareTo(MyCommentVO myCommentVO) {
        return (int) (myCommentVO.getCTime().toInstant(ZoneOffset.of("+8")).toEpochMilli() - this.getCTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
