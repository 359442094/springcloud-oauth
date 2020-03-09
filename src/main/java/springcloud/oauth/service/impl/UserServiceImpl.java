package springcloud.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcloud.oauth.model.entity.User;
import springcloud.oauth.model.mapper.UserMapper;
import springcloud.oauth.service.UserRoleService;
import springcloud.oauth.service.UserService;

import java.util.List;

/**
 * @author CJ
 * @date 2020/3/9
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUserName(String name) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,name);
        List<User> users = userMapper.selectList(queryWrapper);
        if(users.size()>0){
            return getArray(users);
        }
        return users.get(0);
    }

    @Override
    public User loadUserByUserName(String name, String pwd) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,name);
        queryWrapper.eq(User::getPwd,pwd);
        List<User> users = userMapper.selectList(queryWrapper);
        if(users.size()>0){
            return getArray(users);
        }
        return users.get(0);
    }

    private User getArray(List<User> users){
        List<String> roles = userRoleService.getRoles(users.get(0).getId());
        String[] userRoles=new String[roles.size()];
        userRoles = roles.toArray(userRoles);
        users.get(0).setRoles(userRoles);
        return users.get(0);
    }

}
