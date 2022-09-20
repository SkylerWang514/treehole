package com.shudong.treehole.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shudong.treehole.entity.Userinfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author treehole
 * @since 2022-06-05
 */
public interface UserinfoService{
    /**
    * @Description: 通过用户id查询用户是否存在
    * @Param: [uid]
    * @return: bool
    * @Author: 王珺玉
    * @Date: 05/06/2022
    */
    public Boolean checkUserInfo(Long uid);

    /**
    * @Description: 添加用户信息
    * @Param: [uName,avatar,uOpenId]
    * @return: uid
    * @Author: 王珺玉
    * @Date: 05/06/2022
    */
    Long saveInfoAndGetID(String uName,String avatar, String uOpenId);
}
