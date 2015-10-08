package com.huan.helloworld.service;

import com.huan.helloworld.mappers.SlideMapper;
import com.huan.helloworld.model.Slides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
@Service
@Transactional
public class SlideService {
    @Autowired
    SlideMapper slideMapper;


    public Slides findById(int slideId) {
        return slideMapper.findById(slideId);
    }

    public List<Slides> findAll() {
        return slideMapper.findAll();
    }
}
