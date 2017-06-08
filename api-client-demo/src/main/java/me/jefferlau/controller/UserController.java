package me.jefferlau.controller;

import lombok.extern.slf4j.Slf4j;
import me.jefferlau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create on 2017-06-02.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("add/{a}/{b}")
    public String add(@PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        if (log.isDebugEnabled()) {
            log.debug("compute {} + {}", a, b);
        }
        return userService.add(a, b).toString();
    }
}
