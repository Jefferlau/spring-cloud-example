package me.jefferlau.clients;

import me.jefferlau.models.User;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class UserClientHystrix implements UserClient {
    @Override
    public PagedResources<User> findAll() {
        return null;
    }

    @Override
    public List<User> findById(@RequestParam("id") String id) {
        return null;
    }

    @Override
    public void createUser(@RequestBody User user) {

    }

    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -9999;
    }
}
