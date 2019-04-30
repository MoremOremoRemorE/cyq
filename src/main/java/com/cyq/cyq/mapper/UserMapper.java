package com.cyq.cyq.mapper;

import com.cyq.cyq.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //获取用户名单
    public List<User> getUser() throws Exception;

    //根据id删除用户
    public void deleteUser(String id)throws Exception;

    //新增用户
    public void addUser(User user)throws Exception;

    //根据id得到用户
    public User getUserByName(String  username) throws  Exception;

    //判断用户名是否被注册
    public int regiterByName(@Param("username")String username);

    //查询用户数量
    public int getuserCount();

    //根据条件得到用户列表
    public List<User> getUserByName1(String  username) throws Exception;

    public void editUserById( String id) throws Exception;

    public void editUser(User user) throws Exception;

   // public void addUserRole(UserRole userrole)throws Exception;

    public String selectUserId()throws Exception;

    public void editUserByRole(User user)throws Exception;

    public  User getUserById(String id);
}
