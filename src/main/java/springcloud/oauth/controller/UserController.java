package springcloud.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.oauth.config.AuthProperties;
import springcloud.oauth.dto.LoginRequest;
import springcloud.oauth.dto.LoginResponse;
import springcloud.oauth.model.entity.User;
import springcloud.oauth.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

/**
 * @author CJ
 * @date 2020/3/9
 */
@RestController
public class UserController {

    @Autowired
    private AuthProperties authProperties;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin",method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/user",method = RequestMethod.GET)
    public String user(){
        return "user";
    }

    @RequestMapping(value = "${security.oauth2.resource.login}",method = RequestMethod.POST)
    public LoginResponse login(
            LoginRequest request,
            HttpServletResponse response){
        //Http Basic 验证
        String clientAndSecret = authProperties.getWithClient()+":"+authProperties.getSecret();
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = "Basic " + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", clientAndSecret);
        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(request.getUsername()));
        map.put("password", Collections.singletonList(request.getPassword()));
        map.put("grant_type", Collections.singletonList(authProperties.getGrantType()));
        map.put("scope", Arrays.asList(authProperties.getScope()));
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
        //获取 Token
        ResponseEntity<OAuth2AccessToken> body = restTemplate.exchange(authProperties.getTokenInfoUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
        OAuth2AccessToken oAuth2AccessToken = body.getBody();
        response.addCookie(new Cookie("access_token", oAuth2AccessToken.getValue()));
        response.addCookie(new Cookie("refresh_token", oAuth2AccessToken.getRefreshToken().getValue()));

        User user = userService.loadUserByUserName(request.getUsername(), request.getPassword());

        return new LoginResponse(user,body);

    }

}
