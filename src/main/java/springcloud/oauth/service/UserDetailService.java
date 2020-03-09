package springcloud.oauth.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import springcloud.oauth.model.entity.User;

import java.util.List;

/**
 * @author CJ
 * @date 2020/3/9
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.loadUserByUserName(name);
        if(!StringUtils.isEmpty(user)){
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRoles());
            return new org.springframework.security.core.userdetails.User(user.getName(),user.getPwd(),authorities);
        }
        throw new UsernameNotFoundException("username is not found");
    }

}
