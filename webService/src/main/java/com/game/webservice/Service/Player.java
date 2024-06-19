package com.game.webservice.Service;

import com.alibaba.fastjson.JSONObject;
import com.game.webservice.Controller.WebSocketController;

public class Player {
   Integer id;

    public Player(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void sendMes(String event, Object text){
        JSONObject resp=new JSONObject();
        resp.put("event",event);
        resp.put(event,text);
        WebSocketController webSocketController = WebSocketController.wsuser.get(this.id);
        webSocketController.SendMes(resp.toJSONString());
    }
}
