package com.youth.printer.printer.base;

/**
 * Created by youth on 15/8/25.
 */

public class PrinterView {

    public int width;
    public int height;
    protected int x;
    protected int y;

    protected LayoutPramas layoutPramas;

    public LayoutPramas getLayoutPramas() {
        return layoutPramas;
    }

    public void setLayoutPramas(LayoutPramas layoutPramas) {
        this.layoutPramas = layoutPramas;
    }

    public void onMeasure(int width,int height){
        this.height = height;
        this.width = width;
    }

    public void onLayout(int x,int y){
       this.x =x;
        this.y = y;
    }

    public String onDraw(){
        return null;
    }

    public class LayoutPramas{

        private int marginTop;
        private int marginBottom;
        private int marginLeft;
        private int marginRight;

        public LayoutPramas(int marginLeft,int marginRight,int marginTop,int marginBottom){

            this.marginTop = marginTop;
            this.marginBottom = marginBottom;
            this.marginLeft = marginLeft;
            this.marginRight = marginRight;
        }

        public int getMarginTop() {
            return marginTop;
        }

        public int getMarginBottom() {
            return marginBottom;
        }

        public int getMarginLeft() {
            return marginLeft;
        }

        public int getMarginRight() {
            return marginRight;
        }
    }

}
