package springcloud.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import springcloud.oauth.model.entity.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    List<String> getRoles(Integer userId);

}
