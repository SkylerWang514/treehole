package com.shudong.treehole.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shudong.treehole.entity.Thumbup;
import com.shudong.treehole.entity.Treehole;
import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.CommentMapper;
import com.shudong.treehole.mapper.ThumbupMapper;
import com.shudong.treehole.mapper.TreeholeMapper;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.vo.TreeholeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("Treehole")
public class TreeholeServiceImpl implements TreeholeService {

    @Autowired
    TreeholeMapper treeholeMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    @Autowired
    ThumbupMapper thumbupMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<TreeholeVO> getTreeholeList(Long uid, Integer page, Integer num) {
        //参数校验
        if ( null == uid || null == page || null == num) return null;
        //判断是否有效
        if (uid <= 0 || page <= 0 || num <= 0) return null;
        if (userinfoMapper.selectById(uid) == null)   //检验userId的有效性
            return null;

        //动态列表（需提取treehole表，userinfo表，thumbup表，comment表，进行多表关联查询）
        List<TreeholeVO> treeholeVOS = new ArrayList<>();
        //mapper中sql语句 limit offset, num 得到偏移量为offset的num个数据
        int offset = (page - 1) * num;
        //取从offset开始的num个数据
        List<Treehole> currentTree = treeholeMapper.selectTreeByTimeDesc(offset, num);
        System.out.println(currentTree);    //获取对应页的treehole表数据
        if(currentTree.size() == 0)     // 检查分页数据的有效性
            return null;

        treeholeVOS = getTreeholeListVOByTreeholeList(currentTree);

        return treeholeVOS;
    }

    @Override
    public int countAll() {
        int res = treeholeMapper.selectCountAll();
        return res;
    }

    @Override
    public List<TreeholeVO> getTreeholeListWithWord(String word) {
        // 合法性判断
        if(Objects.equals(word,"")||word == null){
            return null;
        }
        List<Treehole> treeholeList = new ArrayList<>();
        treeholeList = treeholeMapper.getTreeholeListByWord(word);
        if(treeholeList.size() == 0){
            return null;
        }
        List<TreeholeVO> treeholeVOS;
        // 将TreeholeList转为TreeholeVOList
        treeholeVOS = getTreeholeListVOByTreeholeList(treeholeList);
        return treeholeVOS;
    }

    // 发布动态
    @Override
    public Boolean addTreehole(Long uid, String content, Boolean anonymous) {
        //参数校验
        if ( null == uid || null == content || null == anonymous) return false;
        if (uid <= 0) return false;
        if (userinfoMapper.selectById(uid) == null)   //检验userId的有效性
            return null;

        Treehole treehole = new Treehole();
        treehole.setUid(uid);
        treehole.setTContent(content);
        treehole.setAnonymousOrNot(anonymous);
        treehole.setTTime(LocalDateTime.now());
        treeholeMapper.insert(treehole);
        return true;
    }

    @Override
    public TreeholeVO getTreeholeVOByTid(Long tid) {
        if(tid == null) return null;
        Treehole treehole = new Treehole();
        treehole = treeholeMapper.selectTreeholeByTid(tid);
        TreeholeVO treeholeVO = new TreeholeVO();
        treeholeVO.setTid(treehole.getTid());   // 先把treehole表中能得到的数据赋值给treeholeVO
        treeholeVO.setTContent(treehole.getTContent());
        treeholeVO.setTTime(treehole.getTTime());
        treeholeVO.setAnonymousOrNot(treehole.getAnonymousOrNot());

        Long userid = treehole.getUid();
        Userinfo tempUser = userinfoMapper.selectById(userid);
        treeholeVO.setUName(tempUser.getUName());   // 从userinfo表获取对应昵称和头像
        treeholeVO.setAvatar(tempUser.getAvatar());

        int thumbNum = thumbupMapper.getThumbupNumBytid(treehole.getTid());
        treeholeVO.setThumbNum(thumbNum);
        int commentNum = commentMapper.getCommentNum(treehole.getTid());
        treeholeVO.setCommentNum(commentNum);

        return treeholeVO;
    }

    @Override
    public List<TreeholeVO> getTreeholeVOListByUid(Long uid) {
        if(uid == null) return null;
        if (userinfoMapper.selectById(uid) == null) return null;

        List<Treehole> treeholeList = new ArrayList<>();

        treeholeList = treeholeMapper.selectTreeholeByUid(uid);
        if(treeholeList.size() == 0){
            return null;
        }

        List<TreeholeVO> treeholeVOS = new ArrayList<>();
        treeholeVOS = getTreeholeListVOByTreeholeList(treeholeList);

        return treeholeVOS;
    }

    @Override
    public List<TreeholeVO> getTreeholeListVOByTreeholeList(List<Treehole> treeholes) {
        List<TreeholeVO> treeholeVOM = new ArrayList<>();
        for (Treehole tree: treeholes){
            //treeholeVO 为 临时数据
            TreeholeVO treeholeVO = new TreeholeVO();
            treeholeVO.setTid(tree.getTid());   // 先把treehole表中能得到的数据赋值给treeholeVO
            treeholeVO.setTContent(tree.getTContent());
            treeholeVO.setTTime(tree.getTTime());
            treeholeVO.setAnonymousOrNot(tree.getAnonymousOrNot());

            Long userid = tree.getUid();
            Userinfo tempUser = userinfoMapper.selectById(userid);
            treeholeVO.setUName(tempUser.getUName());   // 从userinfo表获取对应昵称和头像
            treeholeVO.setAvatar(tempUser.getAvatar());

            int thumbNum = thumbupMapper.getThumbupNumBytid(tree.getTid());
            treeholeVO.setThumbNum(thumbNum);
            int commentNum = commentMapper.getCommentNum(tree.getTid());
            treeholeVO.setCommentNum(commentNum);

            // 将生成的整个数据结构添加到要返回的动态列表中
            treeholeVOM.add(treeholeVO);
        }
        return treeholeVOM;
    }

    @Override
    public boolean judgeThumb(Long uid, Long tid) {
        QueryWrapper<Thumbup> query = new QueryWrapper<>();
        query.eq("uid",uid);
        query.eq("tid",tid);
        Thumbup res = thumbupMapper.selectOne(query);
        System.out.println(res);
        if (res == null){
            Thumbup temp = new Thumbup();
            temp.setThumbState(false);
            temp.setTid(tid);
            temp.setUid(uid);
            thumbupMapper.insert(temp);
            return false;
        }else{
            return res.getThumbState();
        }
    }

    @Override
    public void deleteTreeholeByTid(Long tid){

        treeholeMapper.deleteTreeholeByTid(tid);
    }

    @Override
    public Boolean checkTreehole(Long tid) {
        if(Objects.equals(tid,0L) || tid == null){
            return false;
        }
        Treehole treehole = treeholeMapper.selectTreeholeByTid(tid);
        if (treehole == null) return false;
        if (Objects.equals(treehole.getTid(), tid)){
            return true;
        }else return false;
    }
}
