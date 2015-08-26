package com.youth.printer.printer.view;

import com.youth.printer.globle.Const;
import com.youth.printer.printer.base.PrinterView;

/**
 * Created by youth on 15/8/24.
 */
public class Line extends PrinterView {

    private static final String command = "LE";

    private int x;
    private int y;
    private int hLength;
    private int vLength;

    public Line(int x,int y,int hLength,int vLength){
        this.x = x;
        this.y = y;
        this.hLength = hLength;
        this.vLength = vLength;
    }

    @Override
    public String toString() {
        return command+x+ Const.comma+y+Const.comma+hLength+Const.comma+vLength+Const.end;
    }

    @Override
    public void onMeasure(int height, int width) {
        super.onMeasure(0,0);
        this.height = vLength;
        this.width = hLength;
    }

    @Override
    public void onLayout(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String onDraw() {
        return toString();
    }
}
