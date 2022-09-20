package com.shudong.treehole.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shudong.treehole.entity.Thumbup;
import com.shudong.treehole.vo.ThumbUpMeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author treehole
 * @since 2022-06-06
 */
public interface ThumbupService{
    /**
    * @Description: 点赞
    * @Param: [uid，tid]
    * @return: int
    * @Author: 王珺玉
    * @Date: 06/06/2022
    */
    int thumbUp(Long uid,Long tid);

    /**
    * @Description: 获取点赞总数
    * @Param: [tid]
    * @return: int
    * @Author: 王珺玉
    * @Date: 06/06/2022
    */
    int getThumbupBytid(Long tid);

    /**
    * @Description: 取消点赞
    * @Param: [uid,tid]
    * @return: int
    * @Author: 王珺玉
    * @Date: 06/06/2022
    */
    int noThumbUp(Long uid,Long tid);

    /**
    * @Description: 获取点赞我的列表
    * @Param: uid
    * @return: List<ThumbUpMeVO>
    * @Author: 曾薇
    * @Date: 08/06/2022
    */
    List<ThumbUpMeVO> getThumbUpMeVOByUid(Long uid);

    /**
     * @Description: 根据tid删除整个点赞记录
     * @Param: [tid]
     * @return: 无返回值
     * @Author: 李婉婷
     * @Date: 07/06/2022
     */
    void deleteThumbupByTid(Long tid);
}
