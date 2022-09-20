package com.shudong.treehole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shudong.treehole.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author treehole
 * @since 2022-06-07
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    @Select("SELECT * FROM userinfo WHERE uid = #{uid}")
    Userinfo selectUserinfoById(Long uid);

    @Insert("INSERT INTO userinfo (uName,avatar,uOpenId) VALUES (#{uName},#{avatar},#{uOpenId})")
    void insertUserInfo(String uName, String avatar, String uOpenId);

    @Select("select uid from userinfo where uOpenId = #{uOpenId}")
    Long selectUidByUOpenId(String uOpenID);

    @Update("update userinfo set uName=#{uName},avatar=#{avatar},uOpenId=#{openid} where uid = #{uid}")
    Boolean updateUserinfo(String uName, String avatar, String openid, Long uid);

}
