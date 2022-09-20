package com.shudong.treehole.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.shudong.treehole.entity.Treehole;
import com.shudong.treehole.mapper.TreeholeMapper;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.vo.Result;
import com.shudong.treehole.vo.TreeholeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @program: treehole
 * @description: 信息搜索模块
 * @author: 王珺玉
 * @create: 2022-06-09 9:30
 **/

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    TreeholeService treeholeService;

    @GetMapping("/getSearchTreehole")
    @ResponseBody
    public Result getSearchTreehole(String word){
        if (Objects.equals(word,"") || word == null){
            return new Result(10001,"参数为空",null);
        }
        if(!java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(word)) {
            return new Result(10003,"参数类型错误",null);
        }

        List<TreeholeVO> treeholeList = new ArrayList<>();
        treeholeList = treeholeService.getTreeholeListWithWord(word);

        if (treeholeList!=null && !treeholeList.isEmpty()){
            for(int i = 0;i < treeholeList.size();i++){
                TreeholeVO treeholeVO = new TreeholeVO();
                treeholeVO = treeholeList.get(i);
                if (treeholeVO.getAnonymousOrNot()){
                    treeholeVO.setUName("匿名用户");
                    treeholeVO.setAvatar("../../icons/toy2.png");
                    treeholeList.set(i,treeholeVO);
                }
            }
            Collections.sort(treeholeList);
            return new Result(0,"成功！",treeholeList);
        }else return new Result(50001,"数据未找到",null);
    }
}
