package com.huan.helloworld.mappers;

import com.huan.helloworld.model.Slides;
import com.huan.helloworld.model.Story;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by happy on 7/22/2015.
 */
public interface StoryMapper {
    @Select(
            "SELECT * FROM story"
    )
    List<Story> findAll();

    @Select("SELECT * FROM story where uniName=#{uniName}")
    Story findByUniName(String uniName);
}
