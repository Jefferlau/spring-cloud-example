package application;

import me.jefferlau.ClientStarter;
import me.jefferlau.clients.UserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientStarter.class)
@WebAppConfiguration
public class UserClientTests {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserClient userClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddByClient() {
        Integer add = userClient.add(10, 20);
        System.out.println(add);
        assertThat(30, is(equalTo(add)));
    }

    @Test
    public void testAddByTemplate() {
       String body = restTemplate.getForEntity("http://discovery/user/add?a=10&b=20", String.class).getBody();
       System.out.println(body);
       assertThat(body, is(equalTo("30")));
    }

}
