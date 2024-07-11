package com.example.backend.service;
import java.util.Date;

import com.example.backend.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
* 用户服务测试
* */
@SpringBootTest
class UserServiceTest {


    @Resource
    private UserService userService;


    @Test
    public void testAddUser(){
        User user=new User();
        user.setUsername("");
        user.setUserAccount("wangP");
        user.setAvatarUrl("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setPhone("123");
        user.setEmail("456");
        user.setUserRole(1);
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);


    }

    @Test
    void userRegister() {
        //测试
        //1.非空
        String userAccount="yupi";
        String userPassword="";
        String checkPassword="12345678";
        String planetCode="1";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        //2.账户长度小于4位
        userAccount="yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        //3.密码长度不小于8位
        userAccount="yupi";
        userPassword="123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        //4.不包含特殊字符、密码和校验码相同
        userAccount="yu pi";
        userPassword="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        checkPassword="123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        //5账户不能重复
        userAccount="dogYupi";
        checkPassword="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        //注册成功
        userAccount="yupi";
        //userPassword="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertTrue(result>0);









    }
}