package com.game.webservice.Service;

import com.game.webservice.Dao.GamerDetails;
import com.game.webservice.Dao.User;
import com.game.webservice.Service.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    public Map<String,String> getToken(String username,String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        GamerDetails gamerDetails = (GamerDetails) authenticate.getPrincipal();
        User user= gamerDetails.getUser();
        String jwt = JwtUtil.createJWT(Integer.toString(user.getId()));
        Map<String, String> map = new HashMap<>();
        map.put("mes","success");
        map.put("token",jwt);
        return map;
    }
    public Map<String,String> getInfo(){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        GamerDetails principal =(GamerDetails) usernamePasswordAuthenticationToken.getPrincipal();
        User user= principal.getUser();
        Map<String, String> map = new HashMap<>();
        map.put("mes","success");
        map.put("id",Integer.toString(user.getId()));
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        return map;
    }

}
