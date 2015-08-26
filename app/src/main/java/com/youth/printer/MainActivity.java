package com.youth.printer;


import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.youth.printer.bluetooth.BluetoothService;
import com.youth.printer.bluetooth.DeviceListActivity;
import com.youth.printer.example.WaybillFormatAbsoluteUtil;
import com.youth.printer.globle.Const;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "youth--->";

    private BluetoothAdapter mBluetoothAdapter = null;

    private BluetoothService service;

    private String deviceName = "";


    private ActionBar actionBar;
    private Button bluetooth;
    private Button test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initBluetooth();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, Const.REQUEST_ENABLE_BT);
        } else {
            if (service == null) initService();
        }
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if (service != null) {
            if (service.getState() == BluetoothService.STATE_NONE) {
                service.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (service != null) service.stop();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Const.REQUEST_CONNECT_DEVICE:

                if (resultCode == Activity.RESULT_OK) {
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    Log.i(TAG,"address : "+address);
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                    service.connect(device);
                }
                break;
            case Const.REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    initService();
                } else {
                    Toast.makeText(this,"蓝牙未启动. 退出系统.", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void initView(){
        bluetooth = (Button)findViewById(R.id.bluetooth_button);
        test = (Button)findViewById(R.id.test_button);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        actionBar = getSupportActionBar();
        actionBar.setTitle("蓝牙未连接");
    }

    private void initBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(isSupport()){

        }
    }

    private void initService(){
        service = new BluetoothService(this,handler);
    }

    private void initListener(){
        bluetooth.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    private void sendMessage(byte[] buffer) {

        if (service.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this,"蓝牙未连接", Toast.LENGTH_SHORT).show();
            return;
        }
        service.write(buffer);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bluetooth_button:
                Intent serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, Const.REQUEST_CONNECT_DEVICE);
                break;
            case R.id.test_button:
            {
                try {
                    WaybillFormatAbsoluteUtil util = new WaybillFormatAbsoluteUtil(2700,"210091943093","021x","123456789123456789","张三","12345678910","上海市  上海市 青浦区 花絮公路3208弄28号","耐克旗舰店","12345678910","湖北省黄冈市黄州区");
                    String res = util.getRes();
                    Log.i("res",res);
                    byte [] buffer = res.getBytes();
                    if (buffer.length > 0)
                    {
                        sendMessage(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                break;
        }
    }

    private boolean isSupport(){
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "不支持蓝牙", Toast.LENGTH_LONG).show();
            finish();
            return false;
        }else{
            return true;
        }
    }




    // The Handler that gets information back from the BluetoothChatService
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
                            actionBar.setTitle("连接到: " + deviceName);
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            actionBar.setTitle("正在连接");
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            actionBar.setTitle("未连接");
                            break;
                    }
                    break;
                case Const.MESSAGE_WRITE:

                    break;
                case Const.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    break;
                case Const.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    deviceName = msg.getData().getString(Const.DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), "Connected to "
                            + deviceName, Toast.LENGTH_SHORT).show();
                    break;
                case Const.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(Const.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
