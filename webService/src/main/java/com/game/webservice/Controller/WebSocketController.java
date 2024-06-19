package com.game.webservice.Controller;

import com.alibaba.fastjson.JSONObject;
import com.game.webservice.Controller.Utils.JwtUtil;
import com.game.webservice.Dao.User;
import com.game.webservice.Dao.UserMapper;
import com.game.webservice.Service.ChessGame;
import com.game.webservice.Service.Player;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketController {

    private static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketController.userMapper=userMapper;
    }


    public static ConcurrentHashMap<Integer,WebSocketController> wsuser=new ConcurrentHashMap<>();
    public static CopyOnWriteArraySet<User> userpool=new CopyOnWriteArraySet<>();
    User user=null;
    Session session=null;
    ChessGame chessGame=null;

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session=session;
        Integer id= JwtUtil.tokenToId(token);
        this.user= userMapper.findById(id.toString());
        if (user !=null){
            wsuser.put(id,this);

            System.out.println(user.getUsername()+" is connected");
        }else {
            this.session.close();
        }

    }

    @OnClose
    public void onClose() {
        // 关闭链接
        if(this.user !=null){
            wsuser.remove(this.user.getId());
            userpool.remove(this.user);
        }
        System.out.println(this.user.getId()+"disconnect");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws InterruptedException {
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if("start-matching".equals(event)){
            StartMatching();
        } else if ("stop-matching".equals(event)) {
            StopMatching();
        }else if("down".equals(event)){
            SetChess(data.getJSONObject("position").getString("x"),
                    data.getJSONObject("position").getString("y"));
        }

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

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void StartMatching() throws InterruptedException {
        System.out.println("start matching");
        userpool.add(this.user);
        while (userpool.size()>=2){
            System.out.println("size>=2");
            Iterator<User> it=userpool.iterator();
            User a=it.next(),b= it.next();
            userpool.remove(a);
            userpool.remove(b);
            JSONObject respa=new JSONObject();

            respa.put("event","match_success");
            respa.put("oppont_name",b.getUsername());
            respa.put("turn",0);
            wsuser.get(a.getId()).SendMes(respa.toJSONString());

            JSONObject respb=new JSONObject();
            respb.put("event","match_success");
            respb.put("oppont_name",a.getUsername());
            respa.put("turn",1);
            wsuser.get(b.getId()).SendMes(respb.toJSONString());

            //匹配成功后应该每一对组合对应一个game
            ChessGame chessTemp=new ChessGame(new Player(a.getId()),new Player(b.getId()));
            wsuser.get(a.getId()).setChessGame(chessTemp);
            wsuser.get(b.getId()).setChessGame(chessTemp);
            chessTemp.InitChess();
            Thread.sleep(150);
            chessTemp.start();
        }
    }
    public void StopMatching(){
        System.out.println("stop match");
    }
    public void SetChess(String x,String y){
        if (this.chessGame.getPlayerA().getId().equals(user.getId())){
            this.chessGame.Set_Chess(Integer.parseInt(x),Integer.parseInt(y),0);
        }else if (this.chessGame.getPlayerB().getId().equals(user.getId())){
            this.chessGame.Set_Chess(Integer.parseInt(x),Integer.parseInt(y),1);
        }
    }

}
