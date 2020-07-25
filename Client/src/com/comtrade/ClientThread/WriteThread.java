package com.comtrade.ClientThread;


import com.comtrade.transfer.TransferClass;
import com.comtrade.ClientCommunication.ClientCommunication;

import java.io.IOException;

public class WriteThread extends Thread {


    private  TransferClass transferClass;

    public WriteThread(TransferClass transferClass) {
        this.transferClass = transferClass;

        this.start();

    }

    @Override
    public void run() {

        try {
        	ClientCommunication.getInstance().send(transferClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}