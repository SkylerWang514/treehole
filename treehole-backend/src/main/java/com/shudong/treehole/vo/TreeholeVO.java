package com.shudong.treehole.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/* 动态列表信息 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    TreeholeVO implements Comparable<TreeholeVO>{
    private Long tid;       // 树洞id
    private String uName;   // 昵称
    private String avatar;  // 头像
    private Boolean anonymousOrNot;     // 是否匿名
    private String tContent;            // 树洞内容

    @JsonFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime tTime;        // 发布时间
    private int thumbNum;   // 点赞数
    private int commentNum; // 评论数

    @Override
    public int compareTo(TreeholeVO treeholeVO) {
        return (int) (treeholeVO.getTTime().toInstant(ZoneOffset.of("+8")).toEpochMilli() - this.getTTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
