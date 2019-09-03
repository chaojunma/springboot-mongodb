package com.mk.service.impl;

import com.mk.entity.User;
import com.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void updateById(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("user_name", user.getUserName());
        mongoTemplate.updateFirst(query, update, User.class);
    }

    public void delete(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_name").is(userName));
        mongoTemplate.remove(query, User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Map<String,Object> pageList(Integer pageNum, Integer pageSize) {
        Map<String,Object> result = new HashMap<>();
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("id"))).skip((pageNum - 1) * pageSize).limit(pageSize);
        List<User> data = mongoTemplate.find(query, User.class);
        long count = mongoTemplate.count(query, User.class);
        result.put("data", data);
        result.put("count", count);
        return result;
    }
}
