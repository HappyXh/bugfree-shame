package com.huan.helloworld.service;

import com.huan.helloworld.mappers.SlidePageMapper;
import com.huan.helloworld.model.SlidePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
@Service
@Transactional
public class SlidePageService {
    @Autowired
    SlidePageMapper slidePageMapper;


    public SlidePage findById(int slideId) {
        return slidePageMapper.findById(slideId);
    }

    public List<SlidePage> fiinAll() {
        return slidePageMapper.findAll();
    }
}
