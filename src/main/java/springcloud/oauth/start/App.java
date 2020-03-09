package springcloud.oauth.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author CJ
 * @date 2020/3/9
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "springcloud.oauth"
})
@MapperScan(basePackages = {
        "springcloud.oauth.model.mapper"
})
public class App {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate getRestTemplate(){
        return restTemplateBuilder.build();
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
