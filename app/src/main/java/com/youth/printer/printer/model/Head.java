package com.youth.printer.printer.model;

/**
 * Created by youth on 15/8/24.
 */
public class Head {


    private String command = "";

    public Head(String command){
        this.command = command;
    }

    public Head(){

    }

    @Override
    public String toString() {

        if(command == null || command.equals("")){
            return "I8,1,001\n" +
                    "Q1438,20\n" +
                    "q799\n" +
                    "S20\n" +
                    "H10\n" +
                    "ZB\n" +
                    "R0,0\n";
        }else{
            return "I8,1,001\n" +
                    "Q1438,20\n" +
                    "q799\n" +
                    "S20\n" +
                    "H10\n" +
                    "ZB\n" +
                    "R0,0\n"+
                    command+"\n";
        }
    }
}
