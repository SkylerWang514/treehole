package com.shudong.treehole.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shudong.treehole.entity.Treehole;
import com.shudong.treehole.vo.TreeholeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author treehole
 * @since 2022-06-06
 */
public interface TreeholeService{

    /**
     * 功能：获取发现页对应部分的动态列表
     * 传入参数要求：非空；uid要经过token检验，并且确定此用户没有被禁用；page和num必须大于0；
     *              uid有效；同时要检测对应页是否有数据，即检测page和num的有效性
     * 返回值：获取成功，则返回对应动态列表；失败或对应页已经没有数据则返回空值
     **/
    List<TreeholeVO> getTreeholeList(Long uid, Integer page, Integer num);

    int countAll();

    /**
    * @Description: 搜索对应关键字的树洞
    * @Param: [word]
    * @return: [List<Treehole>]
    * @Author: 王珺玉
    * @Date: 06/06/2022
    */
    List<TreeholeVO> getTreeholeListWithWord(String word);

    /**
     * 功能：发布动态，将对应信息存储到treehole表中
     * 传入参数要求：非空；uid要经过token检验，并且确定此用户没有被禁用；
     * 返回值：存储成功，则返回true
     **/
    Boolean addTreehole(Long uid, String content, Boolean anonymous);

    /**
    * @Description: 通过tid获得指定树洞
    * @Param: [tid]
    * @return: java.util.List<com.shudong.treehole.vo.TreeholeVO>
    * @Author: 王珺玉
    * @Date: 07/06/2022
    */
    TreeholeVO getTreeholeVOByTid(Long tid);

    /**
     * @Description: 获得指定uid的树洞列表
     * @Param: [uid]
     * @return: List<TreeholeVO>
     * @Author: 王珺玉
     * @Date: 07/06/2022
     */
    List<TreeholeVO> getTreeholeVOListByUid(Long uid);

    /**
    * @Description: 通过treeholeList获得treeholeListVO
    * @Param: List<Treehole>
    * @return: List<TreeholeVO>
    * @Author: 王珺玉
    * @Date: 07/06/2022
    */
    List<TreeholeVO> getTreeholeListVOByTreeholeList(List<Treehole> treeholes);

    // 判断某树洞是否被当前登录的用户点赞过
    boolean judgeThumb(Long uid, Long tid);

    /**
     * @Description: 删除指定的treehole(自己的treehole）
     * @Param: [tid]
     * @return: 无返回值
     * @Author: 李婉婷
     * @Date: 08/06/2022
     */
    void deleteTreeholeByTid(Long tid);

    /**
    * @Description: 通过树洞id检查树洞是否存在
    * @Param: [tid]
    * @return: boolean
    * @Author: 王珺玉
    * @Date: 07/06/2022
    */
    Boolean checkTreehole(Long tid);
}
