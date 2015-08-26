package com.youth.printer.printer.layout;

import com.youth.printer.printer.base.PrinterGroupView;
import com.youth.printer.printer.base.PrinterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youth on 15/8/25.
 */

public class PrinterLinearLayout extends PrinterGroupView {

    private boolean isHorizontal = true;

    private List<PrinterView> list = new ArrayList<>();

    public PrinterLinearLayout(int width, int height, boolean isHorizontal) {
        this.width = width;
        this.height = height;
        this.isHorizontal = isHorizontal;
    }


    public void addView(PrinterView view) {
        list.add(view);
    }

    @Override
    public void onMeasure(int width, int height) {
        for (PrinterView view : list) {
            view.onMeasure(width, height);
        }
    }

    @Override
    public void onLayout(int x, int y) {
        int sum = 0;
        if (isHorizontal) {
            for (PrinterView view : list) {
                if (view.getLayoutPramas() != null) {
                    sum += view.getLayoutPramas().getMarginLeft();
                    view.onLayout(sum, y + view.getLayoutPramas().getMarginTop());
                    sum += view.width + view.getLayoutPramas().getMarginRight();
                } else {
                    view.onLayout(sum, y);
                    sum += view.width;
                }
            }
        } else {
            for (PrinterView view : list) {
                if (view.getLayoutPramas() != null) {
                    sum += view.getLayoutPramas().getMarginTop();
                    view.onLayout(x + view.getLayoutPramas().getMarginLeft(), sum);
                    sum += view.height + view.getLayoutPramas().getMarginBottom();
                } else {
                    view.onLayout(x, y + sum);
                    sum += view.height;
                }
            }
        }
    }

    @Override
    public String onDraw() {
        String command = "";
        for (PrinterView view : list) {
            command += view.onDraw();
        }
        return command;
    }
}
