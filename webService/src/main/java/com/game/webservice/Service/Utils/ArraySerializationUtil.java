package com.game.webservice.Service.Utils;

public class ArraySerializationUtil {

    public static String ArraySerialize(Integer [][] chess){
        StringBuilder s = new StringBuilder();
        for (int i=0;i<19;i++){
            for (int j=0;j<19;j++){
                s.append(Integer.toString(chess[i][j]));
            }
            s.append('/');
        }
        return s.toString();
    }
    public static Integer [][] StringtoArray(String s){


        return null;
    }

}
