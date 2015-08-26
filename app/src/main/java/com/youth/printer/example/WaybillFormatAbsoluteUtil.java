package com.youth.printer.example;


import com.youth.printer.printer.model.Foot;
import com.youth.printer.printer.model.Head;
import com.youth.printer.printer.view.Barcode;
import com.youth.printer.printer.view.DottedLine;
import com.youth.printer.printer.view.Line;
import com.youth.printer.printer.view.QRCode;
import com.youth.printer.printer.view.Text;

/**
 * Created by youth on 15/8/24.
 */

public class WaybillFormatAbsoluteUtil {

    private int money;

    private String pen;

    private String toName;

    private String toPhone;

    private String toAddress;

    private String fromName;

    private String fromPhone;

    private String fromAddress;

    private String toAddressZipCode;

    private String waybillId;



    public WaybillFormatAbsoluteUtil(int money, String toAddressZipCode, String pen, String waybillId, String toName, String toPhone, String toAddress, String fromName, String fromPhone, String fromAddress){

        this.pen = pen;
        this.money = money;
        this.toAddressZipCode = toAddressZipCode;
        this.waybillId = waybillId;
        this.fromName = fromName;
        this.fromPhone = fromPhone;
        this.fromAddress = fromAddress;
        this.toName = toName;
        this.toPhone = toPhone;
        this.toAddress = toAddress;
    }

    public String getRes(){
        String res = "";
        res += getHead();

        res += getFoot(getCommand());
        return res;
    }

    private String getCommand(){
        String command = "";
        command += getText(20,20,2,2,"代收货款");
        command += getText(20, 80, 1, 1, "金额:  " + money + "元");
        command += getBarcode(470, 20, 90, toAddressZipCode);
        command += getLine(0, 120, 750, 10);
        command += getQRCode(40, 145, 0, 0, "http://www.baidu.com/");
        command += getDottedLine(260, 130, 2, 200);
        command += getDottedLine(0, 320, 750, 2);
        command += getText(470, 168, 3, 3, pen);
        command += getBarcode(220, 340, 100, waybillId);
        command += getDottedLine(0, 460, 750, 2);
        command += getText(20, 470, 1, 1, "收件人:");
        command += getText(150, 470, 1, 1, toName);
        command += getText(300, 470, 1, 1, toPhone);
        command += getText(150, 500, 1, 1, toAddress);
        command += getDottedLine(0, 560, 700, 2);
        command += getText(20, 580, 1, 1, "寄件人:");
        command += getText(150, 580, 1, 1, fromName);
        command += getText(300, 580, 1, 1, fromAddress);
        command += getText(150, 610, 1, 1, fromPhone);
        command += getDottedLine(0, 650, 700, 2);
        command += getDottedLine(700, 460, 2, 330);
        command += getText(20, 660, 1, 1, "收件人/代收人:");
        command += getDottedLine(350, 650, 2, 140);
        command += getText(380, 660, 1, 1, "签收时间:");
        command += getText(460,740,1,1,"年    月    日");
        command += getText(710,570,1,1,"签");
        command += getText(710,600,1,1,"收");
        command += getText(710,630,1,1,"联");
        command += getLine(0,790,755,15);
        return command;
    }



    private String getHead(){
        Head head = new Head();
        return head.toString();
    }

    private String getFoot(String command){
        Foot foot = new Foot(command);
        return foot.toString();
    }

    private String getText(int x,int y,int hEnlarge,int vEnlarge,String data){
        Text text = new Text(x,y,0,6,hEnlarge,vEnlarge, Text.Style.N,data);
        return text.toString();
    }

    private String getBarcode(int x,int y,int height,String data){
        Barcode barcode = new Barcode(x,y,0,"3",2,6,height,false,data);
        return barcode.toString();
    }

    private String getQRCode(int x,int y,int width,int height,String data){
        QRCode qrCode = new QRCode(x,y,width,height,data);
        return qrCode.toString();
    }

    private String getLine(int x,int y,int hLength,int vLength){
        Line line = new Line(x,y,hLength,vLength);
        return line.toString();
    }

    private String getDottedLine(int x,int y,int hLength,int vLength){
        DottedLine line = new DottedLine(x,y,10,hLength,vLength);
        return line.toString();
    }

}
