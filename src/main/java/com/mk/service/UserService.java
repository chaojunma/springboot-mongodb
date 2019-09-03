package com.mk.service;

import com.mk.entity.User;
import java.util.List;
import java.util.Map;

public interface UserService {

    public void save(User user);

    public void delete(String userName);

    public void updateById(User user);

    public List<User> findAll();

    public Map<String,Object> pageList(Integer pageNum, Integer pageSize);
}
