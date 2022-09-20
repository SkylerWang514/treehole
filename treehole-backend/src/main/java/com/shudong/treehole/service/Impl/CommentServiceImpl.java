package com.shudong.treehole.service.Impl;

import com.shudong.treehole.entity.Comment;
import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.CommentMapper;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.CommentService;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.vo.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("Comment")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public String testComment(){
        return "test comment";
    }


    //消息通知模块
    //获取“我的评论”列表
    @Override
    public List<Comment> getCommentListByUid(Long uid) {
        if(uid == null) return null;

        List<Comment> commentList = new ArrayList<>();
        commentList = commentMapper.selectCommentByUid(uid);

        if (commentList == null) return null;
        else return commentList;
    }

    //消息通知模块
    //通过我的评论列表获得MyCommentVOList
    @Override
    public List<MyCommentVO> getMyCommentVOListByCommentList(List<Comment> commentList) {
        if(commentList == null) return null;
        else {
            List<MyCommentVO> myCommentVOS = new ArrayList<>();
            for(Comment comment:commentList){
                MyCommentVO myCommentVO = new MyCommentVO();
                myCommentVO.setCTime(comment.getCTime());
                myCommentVO.setCContent(comment.getCContent());

                //取treeholeVO
                TreeholeVO treeholeVO = new TreeholeVO();
                treeholeVO = treeholeService.getTreeholeVOByTid(comment.getTid());
                if(treeholeVO.getAnonymousOrNot()) {
                    treeholeVO.setUName("匿名用户");
                    treeholeVO.setAvatar("../../icons/toy2.png");
                }
                myCommentVO.setTreeholeVO(treeholeVO);

                //将新生成的MyCommentVO添加到List<MyCommentVO>中
                myCommentVOS.add(myCommentVO);
            }
            return myCommentVOS;
        }
    }

    //消息通知模块
    //通过树洞列表获取List<CommentMeVO>
    @Override
    public List<CommentMeVO> getCommentMeVOListByTreeholeVOList(List<TreeholeVO> treeholeVOList) {
        if(treeholeVOList == null) return null;

        List<CommentMeVO> commentMeVOS = new ArrayList<>();
        for (TreeholeVO treeholeVO:treeholeVOList) {
            List<Comment> commentList = new ArrayList<>();
            commentList = this.getCommentListByTid(treeholeVO.getTid());

            for (Comment comment:commentList) {
                CommentMeVO commentMeVO = new CommentMeVO();

                //获取直接获取的
                commentMeVO.setUid(comment.getUid());
                commentMeVO.setCTime(comment.getCTime());
                commentMeVO.setCContent(comment.getCContent());
                commentMeVO.setAnonymousOrNot(comment.getAnonymousOrNot());

                //获取头像和姓名
                Userinfo userinfo = new Userinfo();
                userinfo = userinfoMapper.selectUserinfoById(comment.getUid());
                commentMeVO.setUName(userinfo.getUName());
                commentMeVO.setAvatar(userinfo.getAvatar());

                commentMeVO.setTreeholeVO(treeholeVO);

                commentMeVOS.add(commentMeVO);
            }
        }
        return commentMeVOS;
    }

    //消息通知模块
    //通过tid获取该树洞时间逆序排列的评论列表
    @Override
    public List<Comment> getCommentListByTid(Long tid) {
        if (tid == null) return null;
        List<Comment> commentList = new ArrayList<>();
        commentList = commentMapper.selectCommentByTid(tid);
        return commentList;
    }

    @Override
    public List<TreecommentVO> getTreeComments(Long tid) {
        if (tid == null)
            return null;
        List<Comment> comments = new ArrayList<>();
        comments = commentMapper.selectCommentByTid(tid);
        if(comments.isEmpty())
            return null;
        //最终要返回的列表
        List<TreecommentVO> treecommentVOS = new ArrayList<>();
        for (Comment comment: comments){
            TreecommentVO temp = new TreecommentVO();

            temp.setCTime(comment.getCTime());
            temp.setCContent(comment.getCContent());
            temp.setAnonymousOrNot(comment.getAnonymousOrNot());

            Userinfo userinfo = new Userinfo();
            userinfo = userinfoMapper.selectUserinfoById(comment.getUid());
            temp.setUName(userinfo.getUName());
            temp.setAvatar(userinfo.getAvatar());

            treecommentVOS.add(temp);
        }
        return treecommentVOS;
    }

    @Override
    public Boolean publishComment(Long uid, Long tid, String content, Boolean anonymous) {
        if (uid == null || tid == null || content == null)
            return null;
        Comment comment = new Comment();
        comment.setUid(uid);
        comment.setTid(tid);
        comment.setCContent(content);
        comment.setAnonymousOrNot(anonymous);
        comment.setCTime(LocalDateTime.now());
        try {
            commentMapper.insert(comment);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteCommentByTid(Long tid){
        commentMapper.deleteCommentByTid(tid);
    }

}
