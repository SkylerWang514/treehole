package com.shudong.treehole.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author treehole
 * @since 2022-06-07
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("uid")
    private Long uid;
    @TableField("fTime")
    private String fTime;
    @TableField("fContent")
    private String fContent;

    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getfTime() {
        return fTime;
    }
    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getfContent() {
        return fContent;
    }
    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "uid=" + uid +
                ", fTime='" + fTime + '\'' +
                ", fContent='" + fContent + '\'' +
                '}';
    }

}
