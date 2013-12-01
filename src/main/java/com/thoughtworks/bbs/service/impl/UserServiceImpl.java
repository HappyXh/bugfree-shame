package com.thoughtworks.bbs.service.impl;

import com.thoughtworks.bbs.mappers.UserMapper;
import com.thoughtworks.bbs.mappers.UserRoleMapper;
import com.thoughtworks.bbs.model.User;
import com.thoughtworks.bbs.model.UserRole;
import com.thoughtworks.bbs.model.UserValidator;
import com.thoughtworks.bbs.service.ServiceResult;
import com.thoughtworks.bbs.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserValidator validator;
    private SqlSessionFactory factory;

    public UserServiceImpl(SqlSessionFactory factory) {
        this.factory = factory;
        validator = new UserValidator();
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        SqlSession session = factory.openSession();
            try{
                UserMapper userMapper = session.getMapper(UserMapper.class);
                users = userMapper.findAllUsers();
            } finally {
                session.close();
            }

        return users;
    }

    @Override
    public ServiceResult<User> save(User user) {
        Map<String, String> errors = validator.validate(user);
        SqlSession session = factory.openSession();

        if(errors.isEmpty()) {
            try{
                UserMapper userMapper = session.getMapper(UserMapper.class);
                UserRoleMapper userRoleMapper = session.getMapper(UserRoleMapper.class);

                userMapper.insert(user);

                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleName("ROLE_REGULAR");

                userRoleMapper.insert(userRole);

                session.commit();
            } finally {
                session.close();
            }
        }

        return new ServiceResult<User>(errors, user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getByUsername(String username) {
        SqlSession session = factory.openSession();
        User user = null;

        try{
            UserMapper mapper = session.getMapper(UserMapper.class);
            user = mapper.findByUsername(username);
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public ServiceResult<User> update(User user) {
        SqlSession session = factory.openSession();
        ServiceResult<User> serviceResult = null;
        Map<String, String> errors = validator.validate(user);

        if(errors.isEmpty()) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                mapper.update(user);
                session.commit();
            }catch (Exception e) {
                errors.put("SqlError", e.getMessage());
            }
            finally {
                session.close();
            }
        }
        serviceResult = new ServiceResult<User>(errors, user);
        return serviceResult;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean userVerify(User user, String password) {
        return user.getPasswordHash().equals(password);
    }

    @Override
    public boolean password(User user, String newpass) {
        user.setPasswordHash(newpass);
        if(update(user).hasErrors())
            return false;
        return true;
    }
}
