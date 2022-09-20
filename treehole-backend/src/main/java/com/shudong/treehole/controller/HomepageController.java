package com.shudong.treehole.controller;

import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.service.UserinfoService;
import com.shudong.treehole.vo.Result;
import com.shudong.treehole.vo.TreeholeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @program: treehole
 * @description: 个人主页模块
 * @author: 李婉婷
 * @create: 2022-06-07 9:30
 **/

@RestController
@RequestMapping("homepage")
public class HomepageController {

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    UserinfoService userinfoService;

    @GetMapping("/getMyselfTreehole")
    //@PostMapping("/getMyselfTreehole")
    @ResponseBody
    public Result getMyselfTreehole(String id){
        if(id == null || Objects.equals(id,"")) return new Result(10002, "参数不全", null);
        Long uid = null;
        try {
            uid = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(10003, "参数类型错误", null);
        }

        if (!userinfoService.checkUserInfo(uid)) {
            return new Result(80001, "用户不存在", null);
        }

        List<TreeholeVO> treeholeVOList = new ArrayList<>();
        treeholeVOList = treeholeService.getTreeholeVOListByUid(uid);

        Collections.sort(treeholeVOList);

        if (treeholeVOList.size() == 0) return new Result(50001, "数据未找到", null);

        return new Result(0,"成功！",treeholeVOList);

    }

}
