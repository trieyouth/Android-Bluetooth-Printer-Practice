package com.youth.printer.printer.view;

import com.youth.printer.globle.Const;
import com.youth.printer.printer.base.PrinterView;

/**
 * Created by youth on 15/8/24.
 */
public class Barcode extends PrinterView {


    private int x;
    private int y;
    private int direction;
    private String code;
    private int narrowWidth;
    private int broadWidth;
    private int height;
    private boolean printContent;
    private String data;

    private static final String command = "B";

    public Barcode(int x,int y,int direction,String code,int narrowWidth,int broadWidth,int height,boolean printContent,String data){
        this.x =x ;
        this.y = y;
        this.direction = direction;
        this.code = code;
        this.narrowWidth = narrowWidth;
        this.broadWidth = broadWidth;
        this.height = height;
        this.printContent = printContent;
        this.data = data;
    }

    private String getPrint(){
        if (printContent){
            return "B";
        }else{
            return "N";
        }
    }

    private String getData(){
        return "\""+data+"\"";
    }

    @Override
    public String toString() {
        return command+x+ Const.comma+y+Const.comma+direction+Const.comma+code+Const.comma+narrowWidth+Const.comma+broadWidth+Const.comma+height+Const.comma+getPrint()+Const.comma+getData()+Const.end;
    }

    @Override
    public void onMeasure(int height, int width) {
        super.onMeasure(0,0);
        this.height = height;
        this.width = 0;
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
