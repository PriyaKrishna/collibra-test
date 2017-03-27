package com.collibra.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by d-yw44cn on 23/03/2017.
 */
public class TcpServer {
    private static final Integer PORT_NUMBER = 50000;
    private static final Logger LOGGER = Logger.getLogger(TcpServer.class.getName());
    private ServerSocket server = null;

    public void initialise() throws IOException, InterruptedException {
        try {
            server = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to initialize the server " + e.getMessage());
            throw e;
        }
        serveClient();
    }

    private void serveClient() throws IOException, InterruptedException {
        while(true) {
            System.out.println("waiting for connection..");
            ServerSocketConnection ssCon = new ServerSocketConnection(server.accept());
            ServerRunnable sr = new ServerRunnable();
            sr.setConnection(ssCon);
            Thread serverThread = new Thread(sr);
            serverThread.start();
        }
    }

    class  ServerRunnable implements Runnable {
        private ServerSocketConnection connection = null;
        private boolean isConnectionSet = false;
        public void run() {
            try {
                connection.serveClient();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void setConnection(ServerSocketConnection connection) {
            this.connection = connection;
            isConnectionSet = true;

        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        TcpServer tcpServer = new TcpServer();
        tcpServer.initialise();
    }
}
