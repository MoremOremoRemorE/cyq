package com.cyq.cyq.service;

import com.cyq.cyq.model.User;

import java.util.List;

public interface UserService {
    //显示所有用户
    public List<User> getUser()throws Exception;

    //根据id删除用户
    public void deleteUser(String id)throws Exception;

    //新增用户
    public void addUser(User user)throws Exception;

    //username得到用户
    public User getUserByName(String username) throws Exception;

    //判断用户名是否被注册
    public int regiterByName(String username);

    //查询用户数量
    public int getuserCount();

    public List<User> getUserByName1(String  username) throws Exception;

    public void editUserById(String id)throws Exception;

    public void editUser(User user) throws Exception;

   // public void addUserRole(UserRole userrole)throws Exception;

    public String selectUserId()throws Exception;

    public  void editUserByRole(User user)throws Exception;

    public  User getUserById(String id);
}
