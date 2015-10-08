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
            "SELECT * FROM slides"
    )
    List<Story> findAll();

    @Select("SELECT * FROM slides where id=#{id}")
    Story findById(int id);
}
