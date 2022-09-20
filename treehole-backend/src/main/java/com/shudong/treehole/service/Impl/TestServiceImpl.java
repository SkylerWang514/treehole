package com.shudong.treehole.service.Impl;

import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.TestService;
import com.shudong.treehole.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: treehole
 * @description: 测试服务接口
 * @author: 王珺玉
 * @create: 2022-06-08 11:03
 **/
@Service("TestService")
public class TestServiceImpl implements TestService {

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public String testHello(){
        return "Hello World";
    }

    @Override
    public UserInfoVO getUserInfoByID(Long uid) {
        if (uid == 0){
            return null;
        }

        Userinfo userinfo = new Userinfo();
        userinfo = userinfoMapper.selectUserinfoById(uid);

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUid(userinfo.getUid());
        userInfoVO.setUName(userinfo.getUName());
        userInfoVO.setAvatar(userinfo.getAvatar());

        return userInfoVO;
    }

}
