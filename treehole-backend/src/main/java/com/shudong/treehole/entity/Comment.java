package com.shudong.treehole.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "cid", type = IdType.AUTO)
      @TableField("cid")
      private Long cid;

      @TableField("uid")
    private Long uid;

      @TableField("tid")
    private Long tid;

      @TableField("cContent")
    private String cContent;

      @TableField("cTime")
    private LocalDateTime cTime;

      @TableField("anonymousOrNot")
    private Boolean anonymousOrNot;


}
