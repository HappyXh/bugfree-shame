package com.huan.helloworld.mappers;


import com.huan.helloworld.model.Slides;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
public interface SlideMapper {
    @Select("SELECT * FROM slides where id=#{id}")
    Slides findById(int id);

    @Select("SELECT * FROM slides")
    List<Slides> findAll();
}
