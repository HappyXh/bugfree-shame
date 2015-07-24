package com.huan.helloworld.service;

import com.huan.helloworld.mappers.SlideMapper;
import com.huan.helloworld.model.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by happy on 7/25/2015.
 */
@Service
@Transactional
public class SlideService {
    @Autowired
    SlideMapper slideMapper;


    public Slide findById(int slideId) {
        return slideMapper.findById(slideId);
    }
}
