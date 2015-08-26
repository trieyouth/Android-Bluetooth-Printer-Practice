package com.youth.printer.printer.view;

import com.youth.printer.globle.Const;
import com.youth.printer.printer.base.PrinterView;

/**
 * Created by youth on 15/8/24.
 */
public class Text extends PrinterView {

    private static final int TEXT_SIZE = 30;
    private int direction;
    private int font;
    private int hEnlarge;
    private int vEnlarge;
    private String data;
    private Style style;

    public enum Style{
        N,R
    }

    private static final String command = "T";

    public Text(String data){
        this(1,1,data);
    }

    public Text(int hEnlarge,int vEnlarge,String data){
        this(0,0,0,6,hEnlarge,vEnlarge, Style.N,data);
    }

    public Text(int x,int y,int direction,int font,int hEnlarge,int vEnlarge,Style style,String data){
        this.data = data;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.font = font;
        this.hEnlarge = hEnlarge;
        this.vEnlarge = vEnlarge;
        this.style = style;
    }

    private String getStyle(){
        switch (style){
            case N:
                return "N";
            case R:
                return "R";
            default:
                return "N";
        }
    }

    private String getData(){
        return "\""+data+"\"";
    }

    @Override
    public String toString() {
        return command+x+ Const.comma+y+Const.comma+direction+Const.comma+font+Const.comma+hEnlarge+Const.comma+hEnlarge+Const.comma+getStyle()+Const.comma+getData()+Const.end;
    }

    @Override
    public void onMeasure(int height, int width) {
        super.onMeasure(0,0);
        this.height = getHeight(vEnlarge);
        this.width = getWidth(hEnlarge);
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

    private int getWidth(int size){
        return size * TEXT_SIZE * data.length();
    }
    private int getHeight(int size){
        return size * TEXT_SIZE;
    }

}
