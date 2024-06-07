package com.game.webservice.Service;

import com.game.webservice.Dao.*;
import com.game.webservice.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Serve {
    @Autowired
    UserMapper userM;
    final int length=10;
    public  String Check(String usrname,String pwd){
        User user = userM.CheckAdmin(usrname, pwd);
        //可以查到则返回true
        if(user!=null){
            if (!user.isCer()){
                return "user";
            }
            return "admin";
        }else {
            return null;
        }
    }
    public void addAdmin(User admin){
        userM.addAdmin(admin.getUsername(), admin.getPassword(), admin.getProvince(), admin.getJob(),admin.getPhone(),  admin.getEmail(), admin.getSum());
    }
    public User findInfo(String name){
        User user = userM.findInfo(name);
        return user;
    }
    public Integer getAll(){
        int num=userM.getAll().size();
        if(num%length!=0){
            num=num/length+1;
        }else {
            num=num/length;
        }
        return num;
    }
    //分页 设置分页大小
    public List<User> getUsers(int start){
        start=(start-1)*length;
        return userM.getUsers(start,length);
    }
    //模糊匹配查询
    public List<User> findUsers(String name){
        name="%"+name+"%";
        return userM.findUsers(name);
    }
}
