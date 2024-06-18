package com.game.webservice.Service;

import com.alibaba.fastjson.JSONObject;
import com.game.webservice.Controller.WebSocketController;

public class Player {
    WebSocketController webSocketController=null;

    public WebSocketController getWebSocketController() {
        return webSocketController;
    }

    public void setWebSocketController(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    public Player(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }
    public void sendMes(String event,Object text){
        JSONObject resp=new JSONObject();
        resp.put("event",event);
        resp.put(event,text);
        webSocketController.SendMes(resp.toJSONString());
    }
}
