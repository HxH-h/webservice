package com.game.webservice.Service;

import com.game.webservice.Dao.GamerDetails;
import com.game.webservice.Dao.User;
import com.game.webservice.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GamerDetailService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findInfo(username);
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        String pass=user.getPassword();
        pass="{noop}"+user.getPassword();
        user.setPassword(pass);
        return new GamerDetails(user);
    }
}
