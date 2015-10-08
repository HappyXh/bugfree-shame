package com.huan.helloworld.service;

import com.huan.helloworld.mappers.DictionaryMapper;
import com.huan.helloworld.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by happy on 9/28/15.
 */
@Service
@Transactional
public class DictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;

    public String getIdsByWord(String word){
        Dictionary d = dictionaryMapper.findByWord(word);
        if(d == null)
            return null;
        else return d.getIds();
    }
}
