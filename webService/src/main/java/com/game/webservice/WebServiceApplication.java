package com.game.webservice;

import com.game.webservice.Dao.UserMapper;
import com.game.webservice.Service.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);

    }

}
