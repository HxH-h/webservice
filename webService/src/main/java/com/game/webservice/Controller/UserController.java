package com.game.webservice.Controller;

import com.game.webservice.Dao.User;
import com.game.webservice.Service.GameService;
import com.game.webservice.Service.LoginService;
import com.game.webservice.Service.Serve;
import com.game.webservice.Service.Utils.ArraySerializationUtil;
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
    @Autowired
    GameService gameService;
    @GetMapping("/{username}")
    public String test(@PathVariable String username){
        System.out.println(gameService.selectChess(1));
        return ser.findInfo(username).toString();
    }
    @PostMapping ("/token")
    public Map<String,String> getToken(@RequestBody User user){
        System.out.println(user.getUsername()+user.getPassword());
        return loginService.getToken(user.getUsername(),user.getPassword());
    }
    @GetMapping("/info/")
    public Map<String,String> getInfo(){
        return loginService.getInfo();
    }


}
