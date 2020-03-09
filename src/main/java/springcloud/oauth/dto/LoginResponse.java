package springcloud.oauth.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import springcloud.oauth.model.entity.User;

/**
 * @author CJ
 * @date 2020/3/9
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class LoginResponse {

    private User user;
    /**
     * oauth授权信息
     * */
    private ResponseEntity<OAuth2AccessToken> body;

}
