package com.youth.printer.example;

import com.youth.printer.printer.base.PrinterView;
import com.youth.printer.printer.layout.PaperLayout;
import com.youth.printer.printer.layout.PrinterLinearLayout;
import com.youth.printer.printer.model.Head;
import com.youth.printer.printer.view.Text;

/**
 * Created by youth on 15/8/26.
 */
public class WaybillFormatLinearLayoutUtil {

    public String getRes(){
        Text textA = new Text("AAAAAAA");
        Text textB = new Text("BBBBBBB");
        PrinterLinearLayout linearLayout = new PrinterLinearLayout(100,100,true);
        PrinterView.LayoutPramas layoutPramas = textA.new LayoutPramas(0,0,10,0);
        textA.setLayoutPramas(layoutPramas);
        linearLayout.addView(textA);
        linearLayout.addView(textB);
        PrinterLinearLayout linearLayout1 = new PrinterLinearLayout(50,50,false);
        Text textC = new Text("CCCCCCC");
        Text textD = new Text("DDDDDDD");
        linearLayout1.addView(textC);
        linearLayout1.addView(textD);
        linearLayout.addView(linearLayout1);
        PaperLayout layout = new PaperLayout(new Head(),200,500);
        layout.addView(linearLayout);
        return layout.drawPaper();
    }

}
