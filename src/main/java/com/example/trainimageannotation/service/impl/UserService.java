package com.example.trainimageannotation.service.impl;

import com.example.trainimageannotation.dao.UserDao;
import com.example.trainimageannotation.po.User;
import com.example.trainimageannotation.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LENOVO
 */
@Service
public class UserService implements IUserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> showUserList(int offset, int limit) {
        List<User> userList = userDao.selectUserList(offset, limit);
        return userList;
    }

    @Override
    public Long getUserCount() {
        return userDao.selectUserCount();
    }

    @Override
    public User showUserById(Long userId) {
        return userDao.selectUserById(userId);
    }
}
