package com.shudong.treehole.controller;

import com.shudong.treehole.entity.Comment;
import com.shudong.treehole.entity.Treehole;
import com.shudong.treehole.service.CommentService;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.service.UserinfoService;
import com.shudong.treehole.util.SensitiveFilter;
import com.shudong.treehole.vo.Result;
import com.shudong.treehole.vo.TreecommentVO;
import com.shudong.treehole.vo.TreeholeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @program: treehole
 * @description: 动态显示部分
 * @author: 乔鑫龙
 * @create: 2022-06-01 10:50
 **/

@RestController
@RequestMapping("publish")
public class PublishController {
    @Autowired
    CommentService commentService;

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    UserinfoService userinfoService;

    /* 获取动态列表 */
    @GetMapping("/find")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result getTreeholeList(String id, Integer page, Integer num){
    //如果参数有空的情况
        if (id == null || Objects.equals(id,"") ||null == page || null == num)
            return new Result(10002,"参数不全",null);
        //转换成整型
        long uid = Long.parseLong(id);
        //判断参数是否有效
        if (uid <= 0 || page <= 0 || num <= 0)
            return new Result(10004,"参数无效",null);
        //判断用户是否存在
        if (!userinfoService.checkUserInfo(uid))
            return new Result(80001,"用户不存在",null);
        //用来存储动态列表n
        List<TreeholeVO> trees = new ArrayList<>();
        try{
            //获取动态列表
            trees = treeholeService.getTreeholeList(uid, page, num);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        //树为空的时候，数据不正确
        if (trees == null)
            return new Result(50002, "数据有误", null);
        return new Result(0, "ok", trees);
    }

    /* 获取树洞总数 */
    @GetMapping("/find/all")
    @ResponseBody
    public Result countTreeholeNum(){
        int res;
        try{
            //返回树洞总数
            res = treeholeService.countAll();
        }catch (Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        return new Result(0, "ok", res);
    }

    /* 发布动态 */
    @PostMapping("/publish")
    @ResponseBody
    public Result publishTreehole(String id, String content, Boolean anonymous){
        Boolean res;
        //判断参数是否全
        if (id == null || Objects.equals(id,"") || content == null || anonymous == null
                || Objects.equals(content,""))
            return new Result(10002,"参数不全",null);
        long uid = Long.parseLong(id);
        if (uid <= 0)
            //判断uid是否有效
            return new Result(10004,"参数无效",null);
        //判断uid是否存在
        if (!userinfoService.checkUserInfo(uid))
            return new Result(80001,"用户不存在",null);

        // 校验敏感词汇
        SensitiveFilter sensitiveFilter = new SensitiveFilter();
        boolean isSensitive = sensitiveFilter.filter(content);
        if (isSensitive)
            return new Result(50001,"存在敏感词汇",null);

        try{
            //添加树洞信息
            res = treeholeService.addTreehole(uid, content, anonymous);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        if (!res)
            return new Result(30001,"系统业务出现问题",null);
        return new Result(0, "ok", null);
    }

    /* 判断某人是否点赞某树洞 */
    @GetMapping("/judgethumb")
    @ResponseBody
    public Result judgeThumbOrNot(String id, String tid){
        boolean res;
        //判断参数是否全
        if (id == null || Objects.equals(id,"") || tid == null || Objects.equals(tid,""))
            return new Result(10002,"参数不全",null);
        long uid = Long.parseLong(id);
        long ttid = Long.parseLong(tid);
        //判断参数是否有效
        if (uid <= 0 || ttid <= 0)
            return new Result(10004,"参数无效",null);
        //判断用户是否存在
        if (!userinfoService.checkUserInfo(uid))
            return new Result(80001,"用户不存在",null);
        try{
            //点赞结果
            res = treeholeService.judgeThumb(uid, ttid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        System.out.println(res);
        if (!res)
            return new Result(0,"ok","false");
        return new Result(0, "ok", "true");

    }

    /* 获取某树洞的评论列表 */
    @GetMapping("/getcomment")
    @ResponseBody
    public Result getTreeholeComment(String id, String tid){
        //判断参数是否全
        if (id == null || Objects.equals(id,"") || tid == null || Objects.equals(tid,""))
            return new Result(10002,"参数不全",null);
        long uid = Long.parseLong(id);
        long ttid = Long.parseLong(tid);
        List<TreecommentVO> comments = new ArrayList<>();
        //判断参数是否有效
        if (uid <= 0 || ttid <= 0)
            return new Result(10004,"参数无效",null);
        //判断用户是否存在
        if (!userinfoService.checkUserInfo(uid))
            return new Result(80001,"用户不存在",null);
        try{
            //获取评论
            comments = commentService.getTreeComments(ttid);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        return new Result(0,"ok",comments);

    }

    // 发布评论
    @PostMapping("/comment")
    @ResponseBody
    public Result commentTreehole(String uid, String tid, String content, Boolean anonymous){
        //判断参数是否全
        if (uid == null || Objects.equals(uid,"") ||tid == null || Objects.equals(tid,"")
        || content == null || Objects.equals(content ,"") || anonymous == null ){
            return new Result(10002,"参数不全",null);
        }
        long uuid = Long.parseLong(uid);
        long ttid = Long.parseLong(tid);

        boolean res;
        //判断参数是否有效
        if (uuid <= 0 || ttid <= 0)
            return new Result(10004,"参数无效",null);
        //判断用户是否存在
        if (!userinfoService.checkUserInfo(uuid))
            return new Result(80001,"用户不存在",null);

        // 校验敏感词汇
        SensitiveFilter sensitiveFilter = new SensitiveFilter();
        boolean isSensitive = sensitiveFilter.filter(content);
        if (isSensitive)
            return new Result(50001,"存在敏感词汇",null);

        try{
            //发布评论
            res = commentService.publishComment(uuid,ttid,content,anonymous);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(40001, "系统内部错误", null);
        }
        if (!res)
            return new Result(30001,"系统业务出现问题",res);
        return new Result(0,"ok",res);
    }
}
