package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.UserMapper;
import com.cyq.cyq.model.User;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUser() throws Exception {
        return userMapper.getUser();
    }
    public List<User> getNormalUser() throws Exception {
        return userMapper.getNormalUser();
    }
    //根据id删除用户
    @Override
    public void deleteUser(String id) throws Exception {
        userMapper.deleteUser(id);
    }
    //新增用户
    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }

   /* @Override
    public void addUserRole(UserRole userrole) throws Exception {
        userMapper.addUserRole(userrole);
    }*/
    //id得到用户
    @Override
    public User getUserByName(String username) throws Exception{
        return userMapper.getUserByName(username);
    }
    //判断用户名是否被注册
    public  int regiterByName(String username){
        return userMapper.regiterByName(username);
    }

    //查询用户数量
    public int getuserCount(){
        return userMapper.getuserCount();
    };

    public List<User> getUserByName1(String  username) throws Exception{
        return userMapper.getUserByName1(username);
    }

    public void editUserById(String id) throws Exception{
        userMapper.editUserById(id);
    }

    public void editUser(User user) throws  Exception{
        userMapper.editUser(user);
    }

    public String selectUserId()throws  Exception{
        return  userMapper.selectUserId();

    }
    public void editUserByRole(User user)throws  Exception{
        userMapper.editUserByRole(user);

    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

}
