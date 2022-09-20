package com.shudong.treehole.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shudong.treehole.entity.Feedback;


/**
 * <p>
 *  收集用户反馈
 * </p>
 *
 * @author 李婉婷
 * @since 2022-06-09
 */

@Service
public interface FeedbackService{

    /**
     * @Description: 获得全部反馈
     * @Param: uid
     * @return: List<Feedback>
     * @Author: 李婉婷
     * @Date: 09/06/2022
     */
    public List<Feedback> findAll();

    /**
     * @Description: 添加反馈
     * @Param: uid
     * @return: List<Feedback>
     * @Author: 李婉婷
     * @Date: 09/06/2022
     */
    @Transactional
    public void addFeedback(Long uid, String fTime, String fContent);

    /**
     * @Description: 修改反馈
     * @Param: uid
     * @return: List<Feedback>
     * @Author: 李婉婷
     * @Date: 09/06/2022
     */
    @Transactional
    public void updateByUid(Long uid, String fTime, String fContent);

    /**
     * @Description: 删除反馈
     * @Param: uid
     * @return: List<Feedback>
     * @Author: 李婉婷
     * @Date: 09/06/2022
     */
    @Transactional
    public void deleteByUid(Long uid);

    /**
     * @Description: 查询指定用户的反馈
     * @Param: uid
     * @return: List<Feedback>
     * @Author: 李婉婷
     * @Date: 09/06/2022
     */
    public Feedback selectByUid(Long uid);
}
