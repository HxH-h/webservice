package com.game.webservice.Service;


import java.util.concurrent.locks.ReentrantLock;

//对正在进行的棋局进行操作
public class ChessGame extends Thread{
    public Integer [][] chess={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    Player playerA=null;
    Player playerB=null;
    private Integer turn=0;

    public Integer getTurn() {
        return turn;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public ChessGame(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void InitChess(){
        playerA.sendMes("InitChess",this.chess);
        playerB.sendMes("InitChess",this.chess);
    }

    public void Set_Chess(int x,int y){
        this.chess[y][x]=turn%2+1;
        turn++;
        if (turn>=10){
            this.Broadcast("end",null);
            return ;
        }
        System.out.println(turn);
        this.Broadcast("synchronization",this.chess);
        this.Broadcast("start_turn",turn%2);
    }
    public void Broadcast(String event,Object obj){
        playerA.sendMes(event,obj);
        playerB.sendMes(event,obj);
    }

    @Override
    public void run() {
        this.Broadcast("start_turn",0);
        while (turn<10){

        }
        this.Broadcast("end",null);
        System.out.println("yifasong");
        System.out.println("对局结束");
    }
    @Override
    protected void finalize( ) throws Throwable {
        super.finalize();
        System.out.println("对象已销毁");
    }
}
