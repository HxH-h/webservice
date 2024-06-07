package com.game.webservice.Controller;

import com.game.webservice.Service.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Serve ser;
    @GetMapping("/{username}")
    public String test(@PathVariable String username){
        System.out.println(ser.findInfo(username).toString());
        return ser.findInfo(username).toString();
    }


}
