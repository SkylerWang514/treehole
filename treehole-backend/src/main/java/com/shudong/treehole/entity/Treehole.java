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
public class Treehole implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "tid", type = IdType.AUTO)
      private Long tid;

      @TableField("uid")
    private Long uid;

    @TableField("tContent")
    private String tContent;

    @TableField("tTime")
    private LocalDateTime tTime;

    @TableField("anonymousOrNot")
    private Boolean anonymousOrNot;
}
