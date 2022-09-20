package com.shudong.treehole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shudong.treehole.entity.Treehole;

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
public interface TreeholeMapper extends BaseMapper<Treehole> {
    @Select(value = "SELECT * FROM treehole WHERE tContent like '%${word}%'")
    List<Treehole> getTreeholeListByWord(String word);
    @Select("select * from treehole order by tTime desc limit #{startindex},#{pagesize}")
    List<Treehole> selectTreeByTimeDesc(int startindex, int pagesize);

    @Select("select * from treehole where uid = #{uid}")
    List<Treehole> selectTreeholeByUid(Long uid);

    @Select("select * from treehole where tid = #{tid}")
    Treehole selectTreeholeByTid(Long tid);

    @Select("select count(*) from treehole")
    int selectCountAll();

    @Select("select * from treehole where uid = #{uid} ORDER BY tTime DESC")
    List<Treehole> getTreeholeListByUid(Long uid);

    @Select("select tid from treehole where uid = #{uid} ORDER BY tTime DESC")
    List<Long> getTidListByUid(Long uid);

    @Select("select tContent from treehole where tid = #{tid}")
    String getTContentByTid(Long tid);

    @Delete("delete from treehole where tid = #{tid}")
    void deleteTreeholeByTid(Long tid);
}
