package io.github.vshnv.nortviz;

import io.github.vshnv.nortviz.entity.AuthToken;
import io.github.vshnv.nortviz.entity.request.LoginRequest;
import io.github.vshnv.nortviz.entity.request.RegisterRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NortvizAssignmentApplicationTests {

    @LocalServerPort
    private int port;

    private final RestTemplate template = new RestTemplate();
    private AuthToken token;
    @Test
    @Order(0)
    void testUserRegister() {
        final ResponseEntity<String> res = template.postForEntity("http://localhost:" + port + "/api/v1/register", new RegisterRequest("test_username", "test_password", "test_name", "test@gmail.com"), String.class);
        assert res.getStatusCode() == HttpStatus.OK;
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "register endpoint failed");
    }

    @Test
    @Order(1)
    void testUserLogin() {
        final ResponseEntity<AuthToken> res = template.postForEntity("http://localhost:" + port + "/api/v1/login", new LoginRequest("test_username", "test_password"), AuthToken.class);
        assert res.getStatusCode() == HttpStatus.OK;
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "register endpoint failed");
        token = res.getBody();
    }
}
