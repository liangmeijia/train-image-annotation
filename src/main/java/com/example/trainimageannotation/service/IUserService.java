package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.User;

import java.util.List;


public interface IUserService {
    List<User> showUserList(int offset, int limit);
    Long getUserCount();
    User showUserById(Long userId);
}
