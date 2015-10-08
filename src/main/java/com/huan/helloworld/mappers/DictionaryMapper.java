package com.huan.helloworld.mappers;

import com.huan.helloworld.model.Dictionary;
import org.apache.ibatis.annotations.Select;

/**
 * Created by happy on 9/28/15.
 */
public interface DictionaryMapper {
    @Select("SELECT * FROM dictionary where word = #{word}")
    Dictionary findByWord(String word);

}
