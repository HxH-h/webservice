package com.game.webservice.Controller;

import com.game.webservice.Controller.Utils.JwtUtil;
import com.game.webservice.Dao.User;
import com.game.webservice.Dao.UserMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketController {

    private static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketController.userMapper=userMapper;
    }

    public static ConcurrentHashMap<Integer,WebSocketController> wsuser=new ConcurrentHashMap<>();
    User user=null;
    Session session=null;


    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session=session;
        Integer id= JwtUtil.tokenToId(token);
        this.user= userMapper.findById(id.toString());
        if (user !=null){
            wsuser.put(id,this);
            System.out.println(token+" is connected");
        }else {
            this.session.close();
        }

    }

    @OnClose
    public void onClose() {
        // 关闭链接
        if(this.user !=null){
            wsuser.remove(this.user.getId());
        }
        System.out.println(this.user.getId()+"disconnect");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    public void SendMes(String text){
        synchronized (this.session){
            try {
                this.session.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
