package com.example.trainimageannotation.dao;

import com.example.trainimageannotation.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LENOVO
 */
@Mapper
public interface UserDao {
    User selectUserById(Long userId);
    List<User> selectUserList(int offset, int limit);
    Long selectUserCount();
}
