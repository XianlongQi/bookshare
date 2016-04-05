package com.qixl.bookshare.service;

import com.qixl.bookshare.dao.UserDao;
import com.qixl.bookshare.exception.ParameterException;
import com.qixl.bookshare.exception.ServiceException;
import com.qixl.bookshare.model.User;

public class UserService {
    public User login (String userName,String password) throws ParameterException,ServiceException {
        
        ParameterException parameterException = new ParameterException();
        
        if (userName == null || userName.equals("")) {
            System.out.println("userName is 空");
            parameterException.addErrorField("userName", "userName is required");
        }
        if (password == null || password.equals("")) {
            System.out.println("password is 空");
            parameterException.addErrorField("password", "password is required");
        }
        
        if (!parameterException.isErrorField()) {
            throw new ServiceException(1002,"请输入用户名和密码");
            //return null;
        }
        
        UserDao userDao = new UserDao();
        User user =userDao.getUserByName(userName);
        
        if (user == null) {
           throw new ServiceException(1000,"用户不存在");
        }
        
        if (!password.equals(user.getPassword())) {
            throw new ServiceException(1001,"用户密码错误");
        }
        
        
        return user;
    }
    
    

}
