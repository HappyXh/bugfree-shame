package com.huan.helloworld.service;

import com.huan.helloworld.mappers.UserMapper;
import com.huan.helloworld.mappers.UserRoleMapper;
import com.huan.helloworld.model.User;
import com.huan.helloworld.model.UserRole;
import com.huan.helloworld.model.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    private UserValidator validator = new UserValidator();;
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_REGULAR = "ROLE_REGULAR";


    public User get(long id) {
        User user = null;
        user = userMapper.findByUserId(id);

        return user;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        users = userMapper.findAllUsers();
        return users;
    }

    public ServiceResult<User> save(User user) {
        Map<String, String> errors = validator.validate(user);
        if(errors.isEmpty()) {
            User duplicateUser = userMapper.findByUsername(user.getUserName());
            if(duplicateUser!=null){
                errors.put("username", "This username <span class='span-name'>"+user.getUserName()+"</span> is so popular, please change another!");
            }else{
                userMapper.insert(user);
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleName(ROLE_REGULAR);
                userRoleMapper.insert(userRole);
            }
        }
        return new ServiceResult<User>(errors, user);
    }

    public void delete(User user) {

    }

    public User getByUsername(String username) {
        User user = null;
        user = userMapper.findByUsername(username);
        return user;
    }

    public ServiceResult<User> update(User user) {
        Map<String, String> errors = validator.validate(user);

        if(errors.isEmpty()) {
            userMapper.update(user);
        }
        return new ServiceResult<User>(errors, user);
    }

    public boolean passwordVerify(User user, String password) {
        return user.getPasswordHash().equals(password);
    }

    public boolean password(User user, String newpass) {
        user.setPasswordHash(newpass);
        if(update(user).hasErrors())
            return false;
        return true;
    }

    public UserRole getUserRoleById(long id){
        UserRole userRole = null;

        userRole = userRoleMapper.get(id);
        return  userRole;
    }

    public Map<User,String> getAllUsersWithRole(){
        List<User> users = getAll();
        Map <User,String> userWithRole= new TreeMap<User,String>(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (int)(user1.getId()-user2.getId());
            }
        });

        for (User user:users) {
            UserRole userRole = userRoleMapper.get(user.getId());
            userWithRole.put(user, userRole.getRoleName());
        }
        return  userWithRole;
    }

    public void updateUserRole(UserRole userRole) {
        ServiceResult<User> serviceResult = null;
        userRoleMapper.update(userRole);

    }

    public void authoriseUser(long userId) {
        UserRole userRole = getUserRoleById(userId);
        userRole.setRoleName(ROLE_ADMIN);
        updateUserRole(userRole);
    }

    public boolean disable(User user) {
        if( user != null && user.isEnabled()) {
            user.setEnabled(false);
            if(!update(user).hasErrors())
                return true;
        }
        return false;
    }


}
