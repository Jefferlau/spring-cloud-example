package me.jefferlau.clients;

import me.jefferlau.models.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "user", fallback = UserClientHystrix.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    PagedResources<User> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    List<User> findById(@RequestParam("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET,value = "/add")
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
