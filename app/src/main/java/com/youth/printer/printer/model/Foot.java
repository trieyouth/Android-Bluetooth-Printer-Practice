package com.youth.printer.printer.model;

import com.youth.printer.globle.Const;

/**
 * Created by youth on 15/8/24.
 */
public class Foot {


    private static final String print = "W1,1";
    private static final String clear = "N"+ Const.end;

    private String command;

    public Foot(String command){
        this.command = command;
    }

    @Override
    public String toString() {
        return clear+command+print;
    }
}
