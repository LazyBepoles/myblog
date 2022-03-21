package cn.liang.service;

import cn.liang.dao.UserMapper;
import cn.liang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.checkUser(username,password);
        return user;
    }

    @Override
    public User getUser(int id) {
        User user = userMapper.getUser(id);
        return user;
    }
}
