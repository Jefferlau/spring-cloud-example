package me.jefferlau.service.impl;

import me.jefferlau.clients.UserClient;
import me.jefferlau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create on 2017-06-02.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Override
    public Integer add(Integer a, Integer b) {
        return userClient.add(a, b);
    }
}
