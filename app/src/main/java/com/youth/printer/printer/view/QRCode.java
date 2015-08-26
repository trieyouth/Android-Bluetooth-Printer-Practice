package com.youth.printer.printer.view;

import com.youth.printer.globle.Const;
import com.youth.printer.printer.base.PrinterView;

/**
 * Created by youth on 15/8/24.
 */
public class QRCode extends PrinterView {

    private String data;

    private int x;
    private int y;
    private int width;
    private int height;

    private static final String command = "b";

    public QRCode(int x,int y,int width,int height,String data){
        this.data = data;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public QRCode(int width,int height,String data){
        this(0,0,width,height,data);
    }

    public QRCode(String data){
        this(100,100,data);
    }

    @Override
    public void onMeasure(int height, int width) {
        super.onMeasure(0,0);
        this.height = width;
        this.width = height;
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

    private String getData(){
        return "\""+data+"\"";
    }

    @Override
    public String toString() {
        return command+x+ Const.comma+y+Const.comma+"QR"+Const.comma+width+Const.comma+height+Const.comma+"o0,r5,m2,g0,s0"+Const.comma+getData()+Const.end;
    }

}
