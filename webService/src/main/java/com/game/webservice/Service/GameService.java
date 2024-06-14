package com.game.webservice.Service;

import com.game.webservice.Dao.ChessHistory;
import com.game.webservice.Dao.UserMapper;
import com.game.webservice.Service.Utils.ArraySerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GameService {
    @Autowired
    UserMapper userMapper;
    public void SaveChessHistory(Integer [][] chess){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = dateTime.format(formatter).toString();
        String hischess = ArraySerializationUtil.ArraySerialize(ArraySerializationUtil.chess);
        userMapper.SaveChessHistory(1,s,hischess);
    }
    public Integer [][] selectChess(Integer id){
        ChessHistory chessHistory = userMapper.selectChess(id);
        String schess = chessHistory.getChess();
        Integer [][] chess=new Integer[19][19];
        int j=0,k=0;
        String temp="";
        for (int i=0;i<schess.length();i++){
            temp=schess.substring(i ,i+1);
            if(temp.equals("/")){
                j++;
                k=0;
                continue;
            }
            chess[j][k++]=Integer.parseInt(temp);
        }
        return chess;
    }
}
