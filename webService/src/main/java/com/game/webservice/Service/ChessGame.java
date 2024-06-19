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
    public void Playing_chess(Integer i){
            if ((turn=i%2)==0){
                playerA.sendMes("start_turn",turn);

            }else{
                playerB.sendMes("start_turn",turn);
            }
    }
    public void Set_Chess(int x,int y,Integer role){
        this.chess[y][x]=role+1;
        this.Broadcast();
        turn=(role+1)%2;
        System.out.println("role: "+role);
    }
    public void Broadcast(){
        playerA.sendMes("synchronization",this.chess);
        playerB.sendMes("synchronization",this.chess);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000;) {
            if (turn!=2){
                System.out.println(turn);
            }
                if (turn==0){
                    playerA.sendMes("start_turn",turn);
                    turn=2;
                    i++;
                }else if (turn==1){
                    playerB.sendMes("start_turn",turn);
                    turn=2;
                    i++;
                }
        }

    }
}
