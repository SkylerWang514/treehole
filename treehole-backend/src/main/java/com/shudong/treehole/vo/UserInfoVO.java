package com.shudong.treehole.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: treehole
 * @description: 用户信息 = {uid + avatar}
 * @author: 王珺玉
 * @create: 2021-07-14 10:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;
    private String uName;
    private String avatar;
}
