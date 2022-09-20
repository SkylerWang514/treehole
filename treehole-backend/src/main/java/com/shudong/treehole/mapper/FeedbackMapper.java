package com.shudong.treehole.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shudong.treehole.entity.Feedback;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author treehole
 * @since 2022-06-07
 */

public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 查询所有反馈
     * param user
     *
     * @return
     */
    @Select("SELECT * FROM feedback")
    List<Feedback> findAll();

    /**
     * 新增反馈
     * param feedback
     * return
     */
    @Insert("INSERT INTO FEEDBACK(UID,FTIME,FCONTENT) VALUES (#{uid}, #{fTime}, #{fContent})")
    void addFeedback(Long uid, String fTime, String fContent);


    /**
     * 根据uid修改
     * param feedback
     * return
     */
    @Update("UPDATE FEEDBACK SET UID=#{uid},FTIME=#{fTime},FCONTENT=#{fContent} WHERE UID=#{uid}")
    void updateByUid(Long uid, String fTime, String fContent);

    /**
     * 删除
     * param feedback
     * return
     */
    @Delete("DELETE FROM FEEDBACK WHERE UID=#{uid}")
    void deleteByUid(Long uid);

    /**
     * 根据uid查询
     * param feedback
     * return
     */
    @Select("SELECT UID,FTIME,FCONTENT FROM FEEDBACK WHERE UID=#{uid}")
    Feedback selectByUid(Long uid);
}
