package com.shudong.treehole.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.shudong.treehole.service.CommentService;
import com.shudong.treehole.service.ThumbupService;
import com.shudong.treehole.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shudong.treehole.entity.Feedback;
import com.shudong.treehole.service.FeedbackService;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.vo.TreeholeVO;

/**
 * @program: treehole
 * @description: 个人设置模块
 * @author: 李婉婷
 * @create: 2022-06-09 9:30
 **/

@RestController
@RequestMapping("/feedback")
public class SettingsController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    ThumbupService thumbupService;

    @Autowired
    CommentService commentService;

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        List<Feedback> feedbackList = feedbackService.findAll();
        //return feedbackList;
        return new Result(0, "成功", null);
    }

    /**
     * 添加反馈
     *
     * @param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addFeedback(String uid, String fContent) {
        if (uid == null && fContent == null)
            return new Result(10001, "参数为空", null);
        if (uid == null || fContent == null)
            return new Result(10002, "参数不全", null);
        Long uid1;
        try {
            uid1 = Long.parseLong(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }
        if (uid1 <= 0)
            return new Result(10004, "参数无效", null);
        String localTime = LocalDateTime.now().toString();
        feedbackService.addFeedback(uid1, localTime, fContent);
        //return "插入成功";
        return new Result(0, "成功", uid);
    }

    @GetMapping("/deleteTreeholeByTid")
    //@RequestMapping(value = "/deleteTreeholeByTid",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteTreeholeByTid(String tid) {
        if (tid == null)
            return new Result(10001, "参数为空", null);
        Long tid1;
        try {
            tid1 = Long.parseLong(tid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }
        if (tid1 <= 0)
            return new Result(10004, "参数无效", null);
        treeholeService.deleteTreeholeByTid(tid1);
        thumbupService.deleteThumbupByTid(tid1);
        commentService.deleteCommentByTid(tid1);
        return new Result(0, "成功", null);
    }

}
