package com.shudong.treehole.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @program: treehole
 * @description: openId+secret
 * @author: 王珺玉
 * @create: 2022-06-06 09:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenIdJson {
    private String openid;
    private String session_key;
    private int errcode;
    private String errmsg;
}
