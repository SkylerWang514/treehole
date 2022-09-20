package com.shudong.treehole.service;


import com.shudong.treehole.entity.Comment;
import com.shudong.treehole.vo.CommentMeVO;
import com.shudong.treehole.vo.MyCommentVO;
import com.shudong.treehole.vo.TreecommentVO;
import com.shudong.treehole.vo.TreeholeVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author treehole
 * @since 2022-06-06
 */
public interface CommentService{
    String testComment();

    /**
    * @Description: 获取我的评论列表
    * @Param: [Long uid]
    * @return: List<CommentAllVO>
    * @Author: 曾薇
    * @Date: 07/06/2022
    */
    List<Comment> getCommentListByUid(Long uid);

    /**
    * @Description: 通过我的评论列表获得MyCommentVOList
    * @Param: List<Comment>
    * @return: List<MyCommentVO>
    * @Author: 曾薇
    * @Date: 07/06/2022
    */
    List<MyCommentVO> getMyCommentVOListByCommentList(List<Comment> commentList);

    /**
    * @Description: 通过树洞列表获取List<CommentMeVO>
    * @Param: List<TreeholeVO>
    * @return: List<CommentMeVO>
    * @Author: 曾薇
    * @Date: 08/06/2022
    */
    List<CommentMeVO> getCommentMeVOListByTreeholeVOList(List<TreeholeVO> treeholeVOList);

    /**
    * @Description: 通过tid获取该树洞时间逆序排列的评论列表
    * @Param: tid
    * @return: List<Comment>
    * @Author: 曾薇
    * @Date: 08/06/2022
    */
    List<Comment> getCommentListByTid(Long tid);

    /**
     * @Description: 通过tid获取某树洞的所有评论相关信息
     * @Param: tid
     * @return: List<TreecommentVO>
     * @Author: 乔鑫龙
     * @Date: 08/06/2022
     */
    List<TreecommentVO> getTreeComments(Long tid);

    /**
     * @Description: 对某树洞发布评论信息
     * @Param: Long uid, Long tid, String comment, Boolean anonymous
     * @return: boolean
     * @Author: 乔鑫龙
     * @Date: 08/06/2022
     */
    Boolean publishComment(Long uid, Long tid, String content, Boolean anonymous);

    /**
     * @Description: 根据tid删除整条评论记录
     * @Param: [tid]
     * @return: 无返回值
     * @Author: 李婉婷
     * @Date: 08/06/2022
     */
    void deleteCommentByTid(Long tid);
}
