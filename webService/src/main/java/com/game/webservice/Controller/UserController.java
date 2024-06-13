package com.game.webservice.Controller;

import com.game.webservice.Service.LoginService;
import com.game.webservice.Service.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Serve ser;
    @Autowired
    LoginService loginService;
    @GetMapping("/{username}")
    public String test(@PathVariable String username){
        System.out.println(ser.findInfo(username).toString());
        return ser.findInfo(username).toString();
    }
    @GetMapping("/token/{username}/{password}")
    public Map<String,String> getToken(@PathVariable String username,@PathVariable String password){
        System.out.println(username+password);
        return loginService.getToken(username,password);
    }


}
