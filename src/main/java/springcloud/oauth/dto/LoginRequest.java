package springcloud.oauth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author CJ
 * @date 2020/3/9
 */
@ToString
@Getter
@Setter
public class LoginRequest{

    private String username;

    private String password;

}
