package me.jefferlau.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create on 2017-06-02.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index() {
        if (log.isDebugEnabled()) {
            log.debug("Request /");
        }
        return "{}";
    }

}
