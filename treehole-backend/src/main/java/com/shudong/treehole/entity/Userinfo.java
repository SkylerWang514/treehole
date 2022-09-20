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
public class Userinfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "uid", type = IdType.AUTO)
      @TableField("uid")
      private Long uid;

      @TableField("uName")
    private String uName;

      @TableField("avatar")
    private String avatar;

    @TableField("uOpenId")
      private String uopenid;


}
