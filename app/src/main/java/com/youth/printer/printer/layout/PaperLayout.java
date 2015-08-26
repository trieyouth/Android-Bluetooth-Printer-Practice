package com.youth.printer.printer.layout;


import com.youth.printer.printer.base.PrinterView;
import com.youth.printer.printer.model.Foot;
import com.youth.printer.printer.model.Head;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by youth on 15/8/25.
 */
public class PaperLayout {

    private int height;

    private int width;

    private Head head;

    private List<PrinterView> list = new ArrayList<>();


    public PaperLayout(Head head, int height, int width){
        this.height = height;
        this.width = width;
        this.head = head;
    }

    public void addView(PrinterView view){
        list.add(view);
    }


    protected void onMeasure(int width, int height) {
        int sumHeight = 0;
        for(PrinterView view:list){
            view.onMeasure(width,height - sumHeight);
            sumHeight += view.height;
        }
    }

    protected void onLayout(int x, int y) {
        int sumHeight = 0;
        for (PrinterView view : list) {
            if(view.getLayoutPramas() == null){
                view.onLayout(x,y+sumHeight);
                sumHeight += view.height;
            }else{
                sumHeight += view.getLayoutPramas().getMarginTop();
                view.onLayout(x+view.getLayoutPramas().getMarginLeft(),y+sumHeight);
                sumHeight += view.height +view.getLayoutPramas().getMarginBottom();
            }
        }
    }

    public String drawPaper(){
        onMeasure(width,height);
        onLayout(0,0);
        String command = "";
        for(PrinterView view : list){
            command += view.onDraw();
        }
        Foot foot = new Foot(command);
        return head.toString() + foot.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
