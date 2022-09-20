package com.shudong.treehole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shudong.treehole.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author treehole
 * @since 2022-06-07
 */
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select count(*) from comment where tid = #{tid}")
    int getCommentNum(Long tid);

    @Insert("INSERT INTO comment (uid,tid,cContent,cTime,anonymousOrNot) VALUES (#{uid},#{tid},#{cContent},#{cTime},#{anonymousOrNot})")
    void insertNewComment(Long uid, Long cid, String cContent, LocalDateTime cTime, boolean anonymousOrNot);

    @Select("select * from comment where uid = #{uid} ORDER BY cTime DESC")
    List<Comment> selectCommentByUid(Long uid);

    @Select("select * from comment where tid = #{tid} ORDER BY cTime DESC")
    List<Comment> selectCommentByTid(Long tid);

    @Delete("delete from comment where tid = #{tid}")
    void deleteCommentByTid(Long tid);
}
