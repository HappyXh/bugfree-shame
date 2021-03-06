package com.huan.helloworld.mappers;

import com.huan.helloworld.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Insert(
            "INSERT INTO users(username, password, enabled) " +
                    "VALUES (#{userName}, #{passwordHash}, #{enabled})"
    )
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    void insert(User user);

    @Update(
        "UPDATE users " +
        "SET username=#{userName}, password=#{passwordHash}, enabled=#{enabled} " +
        "WHERE id=#{id}"
        )
    void update(User user);

    @Select(
            "SELECT id, username, password as passwordHash, enabled " +
                    "FROM users " +
                    "WHERE username = #{username}"
    )
    User findByUsername(String username);

    @Select(
            "SELECT id, username, password as passwordHash, enabled FROM users"
    )
    List<User> findAllUsers();


    @Select(
            "SELECT id, username, password as passwordHash, enabled " +
                    "FROM users " +
                    "WHERE id = #{id}"
    )
    User findByUserId(long id);

}
