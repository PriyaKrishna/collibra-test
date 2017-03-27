package com.collibra.tcp.client;

import com.collibra.tcp.server.MessageIdEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by d-yw44cn on 23/03/2017.
 */
public class TcpClient {
    private static final String END_OF_INPUT = "STOP";
    private static final Logger LOGGER = Logger.getLogger(TcpClient.class.getName());

    public static void main(String[] args) throws IOException {
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection(args[0].trim(),
                Integer.valueOf(args[1]));
        LOGGER.log(Level.INFO, "Connected to the server");
        clientSocketConnection.printMsgFromServer(clientSocketConnection.getMsgFromServer());
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        while (isInputAvailable(br)) {
            if (br.ready()) {
                input = br.readLine();
                if (!input.equals(MessageIdEnum.CLIENT_END_MESSAGE.getMessage())) {
                    clientSocketConnection.sendToServer(input.trim());
                    clientSocketConnection.printMsgFromServer(clientSocketConnection.getMsgFromServer());
                } else {
                    clientSocketConnection.sendToServer(MessageIdEnum.CLIENT_END_MESSAGE.getMessage());
                    clientSocketConnection.printMsgFromServer(clientSocketConnection.getMsgFromServer());
                    clientSocketConnection.close();
                    break;
                }
            } else if (clientSocketConnection.ready()) {
                clientSocketConnection.printMsgFromServer(clientSocketConnection.getMsgFromServer());
                clientSocketConnection.close();
                break;
            }
        }
    }

    private static boolean isInputAvailable(BufferedReader br) throws IOException {
        long end=System.currentTimeMillis() + 30000;
        while((System.currentTimeMillis() < end) && (!br.ready()));
        return br.ready() ||(System.currentTimeMillis() > end) ;
    }
}
