package com.shudong.treehole.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mysql.cj.xdevapi.Collection;
import com.shudong.treehole.entity.Comment;
import com.shudong.treehole.service.CommentService;
import com.shudong.treehole.service.ThumbupService;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.service.UserinfoService;
import com.shudong.treehole.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @program: treehole
 * @description: 点赞评论模块
 * @author: 王珺玉
 * @create: 2022-06-08 9:30
 **/

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    ThumbupService thumbupService;

    @Autowired
    CommentService commentService;

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    UserinfoService userinfoService;

    @PostMapping("/thumb")
    @ResponseBody
    public Result thumbUp(String uid, String tid) {
        if (Objects.equals(uid, "") || Objects.equals(tid, "") || uid == null || tid == null) {
            return new Result(10002, "参数不全", null);
        } else {
            long uid1;
            long tid1;
            try {
                uid1 = Long.parseLong(uid);
                tid1 = Long.parseLong(tid);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(10003, "参数类型错误", null);
            }
            if (!userinfoService.checkUserInfo(uid1)) {
                return new Result(80001, "用户不存在", null);
            } else if (!treeholeService.checkTreehole(tid1)) {
                return new Result(50001, "数据不存在", null);
            }
            int result = thumbupService.thumbUp(uid1, tid1);
            int thumbNum = thumbupService.getThumbupBytid(tid1);
            switch (result) {
                case 1:
                    return new Result(10002, "参数不全", null);
                case 2:
                    return new Result(80001, "用户不存在", null);
                case 3:
                    return new Result(0, "成功", String.valueOf(thumbNum));
                case 4:
                    return new Result(50002, "数据已存在", null);
            }
        }
        return new Result(40001, "系统内部错误", null);
    }

    /**
     * 消息通知模块-获得 “我的评论” 列表
     *
     * @param uid
     */
    @GetMapping("/getMyComment")
    @ResponseBody
    public Result getMyComment(String uid) {
        if (uid == null || Objects.equals(uid, "")) return new Result(10002, "参数不全", null);

        long uid1 = 0L;
        try {
            uid1 = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }
        if (!userinfoService.checkUserInfo(uid1)) {
            return new Result(80001, "用户不存在", null);
        }

        List<Comment> commentList = commentService.getCommentListByUid(uid1);
        if (commentList.size() == 0) return new Result(50001, "数据未找到", null);

        List<MyCommentVO> myCommentVOS = commentService.getMyCommentVOListByCommentList(commentList);
        if (myCommentVOS.size() == 0) return new Result(50001, "数据未找到", null);

        Collections.sort(myCommentVOS);

        return new Result(0, "成功", myCommentVOS);
    }

    /**
     * 消息通知模块-获得“评论我的”列表
     *
     * @param uid
     */
    @GetMapping("/getCommentMe")
    @ResponseBody
    public Result getCommentMe(String uid) {
        if (uid == null || Objects.equals(uid, "")) return new Result(10002, "参数不全", null);

        long uid1 = 0L;
        try {
            uid1 = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }
        if (!userinfoService.checkUserInfo(uid1)) {
            return new Result(80001, "用户不存在", null);
        }


        List<TreeholeVO> treeholeVOS = new ArrayList<>();
        treeholeVOS = treeholeService.getTreeholeVOListByUid(uid1);

        List<CommentMeVO> commentMeVOS = new ArrayList<>();
        commentMeVOS = commentService.getCommentMeVOListByTreeholeVOList(treeholeVOS);

        if (commentMeVOS.size() == 0) return new Result(50001, "数据未找到", null);

        for (CommentMeVO commentMeVO : commentMeVOS) {
            if (commentMeVO.getAnonymousOrNot()) {
                commentMeVO.setAvatar("../../icons/toy2.png");
                commentMeVO.setUName("匿名用户");
            }
            if (commentMeVO.getTreeholeVO().getAnonymousOrNot()) {
                commentMeVO.getTreeholeVO().setAvatar("../../icons/toy2.png");
                commentMeVO.getTreeholeVO().setUName("匿名用户");
            }
        }

        Collections.sort(commentMeVOS);

        return new Result(0, "成功", commentMeVOS);
    }

    @PostMapping("/noThumb")
    @ResponseBody
    public Result noThumb(String uid, String tid) {
        if (Objects.equals(uid, "") || Objects.equals(tid, "") || uid == null || tid == null) {
            return new Result(10002, "参数不全", null);
        } else {
            long uid1;
            long tid1;
            try {
                uid1 = Long.parseLong(uid);
                tid1 = Long.parseLong(tid);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(10003, "参数类型错误", null);
            }
            if (!userinfoService.checkUserInfo(uid1)) {
                return new Result(80001, "用户不存在", null);
            } else if (!treeholeService.checkTreehole(tid1)) {
                return new Result(50001, "数据不存在", null);
            }
            int result = thumbupService.noThumbUp(uid1, tid1);
            int thumbNum = thumbupService.getThumbupBytid(tid1);
            switch (result) {
                case 1:
                    return new Result(10002, "参数不全", null);
                case 2:
                    return new Result(80001, "用户不存在", null);
                case 3:
                    return new Result(0, "成功", String.valueOf(thumbNum));
                case 4:
                    return new Result(50002, "数据已存在", null);
            }
        }
        return new Result(40001, "系统内部错误", null);
    }

    /**
     * 消息通知模块-获得“点赞我的”列表
     *
     * @param uid
     */
    @GetMapping("/thumbupme")
    @ResponseBody
    public Result getThumbUpMe(String uid) {
        if (uid == null || Objects.equals(uid, "")) {
            return new Result(10002, "参数不全", null);
        }
        Long uid1 = 0L;
        try {
            uid1 = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }
        if (!userinfoService.checkUserInfo(uid1)) {
            return new Result(80001, "用户不存在", null);
        }

        List<ThumbUpMeVO> thumbUpMeVOS = new ArrayList<>();
        thumbUpMeVOS = thumbupService.getThumbUpMeVOByUid(uid1);

        if (thumbUpMeVOS.size() == 0) return new Result(50001, "数据未找到", null);
        return new Result(0, "成功", thumbUpMeVOS);
    }

}
