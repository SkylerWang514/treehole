package com.shudong.treehole.service;


import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.vo.UserInfoVO;

public interface TestService {
    /**
     * @Description: test测试类
     * @Param: []
     * @return: 测试字符串
     * @Author: 王珺玉
     * @Date: 05/06/2022
     */
    String testHello();

    /**
    * @Description: 数据库测试类
    * @Param: [uid]
    * @return: User对象
    * @Author: 王珺玉
    * @Date: 05/06/2022
    */
    UserInfoVO getUserInfoByID(Long uid);

}

