package com.collibra.tcp.client;

import com.collibra.tcp.server.TcpServer;

import java.io.IOException;

/**
 * Created by D-YW44CN on 25/03/2017.
 */
public class BaseTest {
    protected static Thread serverThread;
    static {
        ServerRunnable sr = new ServerRunnable();
        serverThread = new Thread(sr);
        serverThread.start();
    }
    static class  ServerRunnable implements Runnable {
        public void run() {
            try {
                while (true) {
                    TcpServer tcpServer = new TcpServer();
                    tcpServer.initialise();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected void addEdges(ClientSocketConnection clientSocketConnection) throws IOException {

        clientSocketConnection.sendToServer("ADD EDGE A B 5");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE A E 2");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE B C 2");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE C D 3");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE F D 2");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE E F 3");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD EDGE A D 9");
        clientSocketConnection.getMsgFromServer();
    }

    protected void addNodes(ClientSocketConnection clientSocketConnection) throws IOException {
        clientSocketConnection.sendToServer("ADD NODE A");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE B");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE C");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE D");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE E");
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE F");
        clientSocketConnection.getMsgFromServer();
    }
}
