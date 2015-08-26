package com.youth.printer.printer.view;

import com.youth.printer.globle.Const;

/**
 * Created by youth on 15/8/24.
 */
public class WriteLine {


    private final static String command = "LW";

    private int x;
    private int y;
    private int hLength;
    private int vLength;

    public WriteLine(int x, int y, int hLength, int vLength){
        this.x = x;
        this.y = y;
        this.hLength = hLength;
        this.vLength = vLength;
    }

    @Override
    public String toString() {
        return command+x+ Const.comma+y+Const.comma+hLength+Const.comma+vLength+Const.end;
    }

}
