package com.shudong.treehole.service.Impl;

import com.shudong.treehole.entity.Feedback;
import com.shudong.treehole.mapper.FeedbackMapper;
import com.shudong.treehole.service.FeedbackService;
import com.shudong.treehole.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("Feedback")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> findAll() {
        return feedbackMapper.findAll();
    }

    @Override
    public void addFeedback(Long uid, String fTime, String fContent) {
        feedbackMapper.addFeedback(uid, fTime, fContent);
    }

    @Override
    public void updateByUid(Long uid, String fTime, String fContent) {
        feedbackMapper.updateByUid(uid, fTime, fContent);
    }

    @Override
    public void deleteByUid(Long uid) {
        feedbackMapper.deleteByUid(uid);
    }

    @Override
    public Feedback selectByUid(Long uid) {
        return feedbackMapper.selectByUid(uid);
    }
}
