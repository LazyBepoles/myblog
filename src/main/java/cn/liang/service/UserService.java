package cn.liang.service;

import cn.liang.pojo.User;

public interface UserService {

    User checkUser(String username, String password);

    User getUser(int id);
}
