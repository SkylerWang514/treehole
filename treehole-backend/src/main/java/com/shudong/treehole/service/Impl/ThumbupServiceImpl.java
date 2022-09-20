package com.shudong.treehole.service.Impl;

import com.shudong.treehole.entity.Thumbup;
import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.ThumbupMapper;
import com.shudong.treehole.mapper.TreeholeMapper;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.ThumbupService;
import com.shudong.treehole.service.UserinfoService;
import com.shudong.treehole.vo.ThumbUpMeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("Thumbup")
public class ThumbupServiceImpl implements ThumbupService {

    @Autowired
    UserinfoService userinfoService;

    @Autowired
    ThumbupMapper thumbupMapper;

    @Autowired
    TreeholeMapper treeholeMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public int thumbUp(Long uid, Long tid) {
        //参数无效
        if (uid == null || tid == null) return 1;
        //用户不存在
        if (!userinfoService.checkUserInfo(uid)) return 2;
        Thumbup thumbup = thumbupMapper.getThumbUp(uid,tid);
        if (thumbup == null){
            //以前未点赞，新增点赞
            Thumbup thumbup1 = new Thumbup(uid,tid, true);
            thumbupMapper.insert(thumbup1);
            return 3;
        }else if(!thumbup.getThumbState()){
            //以前点过赞，但是是取消赞的状态，修改为已点赞状态
            Thumbup thumbup1 = new Thumbup();
            thumbup1 = thumbup;
            thumbup1.setThumbState(true);
            thumbupMapper.deleteThumbUp(uid,tid);
            thumbupMapper.insert(thumbup1);
            return 3;
        }
        //已经点过赞了
        return 4;
    }

    @Override
    public int getThumbupBytid(Long tid) {
        return thumbupMapper.getThumbupNumBytid(tid);
    }

    @Override
    public int noThumbUp(Long uid, Long tid) {
        //参数无效
        if (uid == null || tid == null) return 1;
        //用户不存在
        if (!userinfoService.checkUserInfo(uid)) return 2;
        Thumbup thumbup = thumbupMapper.getThumbUp(uid,tid);
        if (thumbup == null){
            //以前未点赞，报错
            return 4;
        }else if(thumbup.getThumbState()){
            //以前点过赞，修改为取消赞
            Thumbup thumbup1 = new Thumbup();
            thumbup1 = thumbup;
            thumbup1.setThumbState(false);
            thumbupMapper.deleteThumbUp(uid,tid);
            thumbupMapper.insert(thumbup1);
            return 3;
        }
        //已经点过赞了
        return 4;
    }

    //消息通知模块
    //获取“点赞我的”列表
    @Override
    public List<ThumbUpMeVO> getThumbUpMeVOByUid(Long uid) {
        if(uid == null) return null;

        List<Long> tidList = new ArrayList<>();
        tidList = treeholeMapper.getTidListByUid(uid);

        List<ThumbUpMeVO> thumbUpMeVOS = new ArrayList<>();

        for (Long tid:tidList){
            List<Long> uidList = new ArrayList<>();
            uidList = thumbupMapper.getUidListByTid(tid);

            for (Long uid1: uidList){
                Userinfo userinfo = new Userinfo();
                userinfo = userinfoMapper.selectUserinfoById(uid1);

                String tContent = treeholeMapper.getTContentByTid(tid);

                ThumbUpMeVO thumbUpMeVO = new ThumbUpMeVO();
                thumbUpMeVO.setUName(userinfo.getUName());
                thumbUpMeVO.setAvatar(userinfo.getAvatar());
                thumbUpMeVO.setTContent(tContent);

                thumbUpMeVOS.add(thumbUpMeVO);
            }
        }

        return thumbUpMeVOS;
    }

    @Override
    public void deleteThumbupByTid(Long tid){
        thumbupMapper.deleteThumbupByTid(tid);
    }
}
