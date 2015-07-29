package com.huan.helloworld.mappers;


import com.huan.helloworld.model.SlidePage;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
public interface SlidePageMapper {
    @Select("SELECT * FROM slide where id=#{id}")
    SlidePage findById(int id);

    @Select("SELECT * FROM slide")
    List<SlidePage> findAll();
}
