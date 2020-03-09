package springcloud.oauth.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author CJ
 * @date 2020/3/9
 */

@ToString
@Getter
@Setter
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix = "security.oauth2.resource")
@Component
public class AuthProperties {

    private String tokenInfoUri;

    private String withClient;

    private String secret;

    private String grantType;

    private String scope;

    private String login;

}
