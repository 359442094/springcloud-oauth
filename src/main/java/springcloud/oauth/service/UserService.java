package springcloud.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import springcloud.oauth.model.entity.User;

public interface UserService extends IService<User> {

    User loadUserByUserName(String name);

    User loadUserByUserName(String name,String pwd);

}
