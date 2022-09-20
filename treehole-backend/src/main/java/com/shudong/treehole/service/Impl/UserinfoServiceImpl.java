package com.shudong.treehole.service.Impl;

import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.UserinfoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("Userinfo")
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public Boolean checkUserInfo(Long uid) {
        if(Objects.equals(uid,0L) || uid == null){
            return false;
        }
        Userinfo userinfo = userinfoMapper.selectUserinfoById(uid);
        if (userinfo == null) return false;
        if (Objects.equals(userinfo.getUid(), uid)){
            return true;
        }else return false;
    }

    @Override
    public Long saveInfoAndGetID(String uName, String avatar, String uOpenId) {
        if (uName == null||Objects.equals(uName,"")||avatar == null||Objects.equals(avatar,"")||uOpenId == null||Objects.equals(uOpenId,"")){
            return 0L;
        }
        Long uid = userinfoMapper.selectUidByUOpenId(uOpenId);
        if (uid == null){
            userinfoMapper.insertUserInfo(uName,avatar,uOpenId);
            return userinfoMapper.selectUidByUOpenId(uOpenId);
        }else {
            return uid;
        }
    }


}
