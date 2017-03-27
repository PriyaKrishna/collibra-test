package com.collibra.tcp.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by d-yw44cn on 23/03/2017.
 */
public class ClientSocketConnection {
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    public ClientSocketConnection(String host, Integer port) throws IOException {
        clientSocket = new Socket(host, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendToServer(String msg) throws IOException {
        outToServer.writeBytes(new String(msg.getBytes(), "US-ASCII") + System.getProperty("line.separator"));
    }

    public void printMsgFromServer(String msg) throws IOException {
        System.out.println("<SERVER> " + msg);
    }

    public String getMsgFromServer() throws IOException {
        return new String(inFromServer.readLine().getBytes(), "UTF-8");
    }

    public boolean ready() throws IOException {
        return inFromServer.ready();
    }

    public void close() throws IOException {
        clientSocket.close();
    }
}
