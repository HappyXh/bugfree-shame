package com.huan.helloworld.mappers;

import com.huan.helloworld.model.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserRoleMapper {

    @Insert(
        "INSERT INTO user_roles(userid, rolename) " +
        "VALUES (#{userId}, #{roleName})"
    )
    void insert(UserRole userRole);

    @Select(
        "SELECT userid, rolename FROM user_roles " +
        "WHERE userid=#{userId}"
    )
    UserRole get(Long userId);

    @Update(
            "UPDATE user_roles " +
                    "SET rolename=#{roleName}" +
                    "WHERE userid=#{userId}"
    )
    void update(UserRole userRole);
}
