package com.collibra.tcp.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by d-yw44cn on 23/03/2017.
 */
public class ServerSocketConnection {
    private long startTime;
    private BufferedReader inFromClient;
    private DataOutputStream  outToClient;
    private OutputStreamWriter outputStreamWriter;
    private Socket connection;
    private String clientName;
    private UUID sessionId;
    private static final Logger LOGGER = Logger.getLogger(ServerSocketConnection.class.getName());

    public ServerSocketConnection(Socket socket) throws IOException {
        connection = socket;
        startTime = System.currentTimeMillis();
        inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        outToClient = new DataOutputStream(connection.getOutputStream());
        sessionId = getSessionId(connection);
        socket.setSoTimeout(30000);
    }

    public void serveClient() throws IOException, InterruptedException {
        CommandIdEnum.SERVER_WELCOME_MESSAGE_WITH_PLACEHOLDER.process(null, this);
        String input = null;
        try {
            while((input = this.read()) != null && !input.equals(MessageIdEnum.CLIENT_END_MESSAGE.getMessage())) {
                if (input.contains(MessageIdEnum.CLIENT_INPUT_MESSAGE.getMessage())) {
                    this.setClientName(getClientName(input));
                    CommandIdEnum.SERVER_INTIAL_RESPONSE.process(input, this);
                }  else {
                    CommandIdEnum commnd = CommandIdEnum.checkAndGetNodeCommand(input, this);
                    if (commnd != null) {
                        commnd.process(input, this);
                    } else {
                        CommandIdEnum.SERVER_SORRY_MESSAGE.process(null, this);
                    }
                }
            }
            CommandIdEnum.SERVER_BYE_MESSAGE.process(null, this);
        } catch (SocketTimeoutException e) {
            CommandIdEnum.SERVER_BYE_MESSAGE.process(null, this);
        } finally {
            if (inFromClient != null)
                inFromClient.close();
            if (outToClient != null)
                outToClient.close();
        }
    }

    private String getClientName(String input) {
        return input.substring(MessageIdEnum.CLIENT_INPUT_MESSAGE.getMessage().length(), input.length());
    }

    public void write(String msg) throws IOException {
        outToClient.writeBytes(msg + System.getProperty("line.separator"));
        outToClient.flush();
    }

    public String read() throws IOException {
        return inFromClient.readLine();
    }

    private UUID getSessionId(Socket connection) {
        return UUID.randomUUID();
    }

    public void close() throws IOException, InterruptedException {
        LOGGER.log(Level.INFO, "Closing the connection");
        messageConnectionTermination();
        Thread.sleep(30000);
        connection.close();
    }

    private void messageConnectionTermination() throws IOException {
        write(String.format(CommandIdEnum.SERVER_BYE_MESSAGE.getValue(),
                getClientName(), getEndTime()));
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return System.currentTimeMillis() - startTime;
    }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public Socket getConnection() {
        return connection;
    }

    public String getClientName() {
        return clientName;
    }

    public UUID getSessionId() {
        return sessionId;
    }
}
