package com.zking.ssm.service.impl;

import com.zking.ssm.mapper.UserMapper;
import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper um;
    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return um.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(User record) {
        return um.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer userid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return um.updateByPrimaryKey(record);
    }

    @Override
    public User doLogin(User user) {
        return um.doLogin(user);
    }

    @Override
    public Set<String> getRole(String username) {
        return um.getRole(username);
    }

    @Override
    public Set<String> getPermission(String userName) {
        return um.getPermission(userName);
    }
}
