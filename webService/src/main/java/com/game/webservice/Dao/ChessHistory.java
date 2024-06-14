package com.game.webservice.Dao;

public class ChessHistory {
    Integer id;
    String time;
    String chess;

    @Override
    public String toString() {
        return "ChessHistory{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", chess='" + chess + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChess() {
        return chess;
    }

    public void setChess(String chess) {
        this.chess = chess;
    }
}
