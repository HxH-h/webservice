package com.game.webservice.Controller.Utils;

import io.jsonwebtoken.Claims;

public class JwtUtil {
    public  static Integer tokenToId(String token){
        Integer userid;
        try {
            Claims claims = com.game.webservice.Service.Utils.JwtUtil.parseJWT(token);
            userid =Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userid;
    }
}
