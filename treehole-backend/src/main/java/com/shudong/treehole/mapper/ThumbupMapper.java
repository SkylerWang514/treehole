package com.shudong.treehole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shudong.treehole.entity.Thumbup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author treehole
 * @since 2022-06-07
 */
public interface ThumbupMapper extends BaseMapper<Thumbup> {

    @Select("select count(*) from thumbup where uid = #{uid} and tid = #{tid} and thumbState = 1")
    int getThumbupNum(Long uid, Long tid);

    @Select("select * from thumbup where uid = #{uid} and tid = #{tid}")
    Thumbup getThumbUp(Long uid, Long tid);

    @Delete("delete from thumbup where uid = #{uid} and tid = #{tid}")
    int deleteThumbUp(Long uid, Long tid);

    @Select("select count(*) from thumbup where tid = #{tid} and thumbState = 1")
    int getThumbupNumBytid(Long tid);

    @Select("select uid from thumbup where tid = #{tid} and thumbState = 1")
    List<Long> getUidListByTid(Long tid);

    @Delete("delete from thumbup where tid = #{tid}")
    void deleteThumbupByTid(Long tid);
}
