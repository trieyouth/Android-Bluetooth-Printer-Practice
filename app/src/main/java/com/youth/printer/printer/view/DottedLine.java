package com.youth.printer.printer.view;

import com.youth.printer.printer.base.PrinterView;

/**
 * Created by youth on 15/8/24.
 */
public class DottedLine extends PrinterView {

    private int x;
    private int y;
    private int hLength;
    private int vLength;
    private int offset;

    public DottedLine(int x,int y,int offset,int hLength,int vLength){
        this.x = x;
        this.y = y;
        this.hLength = hLength;
        this.vLength = vLength;
        this.offset = offset;
    }

    public String toString(){
        Line line = new Line(x,y,hLength,vLength);
        String command = line.toString();
        if(hLength > vLength){
            for(int i = offset;i < hLength ;i += 2*offset){
                WriteLine lw = new WriteLine(x+i,y,offset,vLength);
                command += lw.toString();
            }
        }else{
            for(int i = offset;i < vLength ;i += 2*offset){
                WriteLine lw = new WriteLine(x,y+i,hLength,offset);
                command += lw.toString();
            }
        }
        return command;
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
