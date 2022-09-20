package com.shudong.treehole.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Thumbup implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("uid")
      private Long uid;

    @TableField("tid")
    private Long tid;

    @TableField("thumbState")
    private Boolean thumbState;


}
