package com.huan.helloworld.mappers;


import com.huan.helloworld.model.Slide;
import org.apache.ibatis.annotations.Select;

/**
 * Created by happy on 7/25/2015.
 */
public interface SlideMapper {
    @Select("SELECT * FROM slide where id=#{id}")
    Slide findById(int id);
}
