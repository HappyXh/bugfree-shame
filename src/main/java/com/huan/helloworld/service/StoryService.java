package com.huan.helloworld.service;

import com.huan.helloworld.mappers.StoryMapper;
import com.huan.helloworld.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by happy on 7/22/2015.
 */
@Service
@Transactional
public class StoryService {
    @Autowired
    StoryMapper storyMapper;

    public List<Story> getAll(){
        List<Story> storyList=storyMapper.findAll();
        return storyList;
    }
}
