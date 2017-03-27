package com.collibra.tcp.client;

import com.collibra.tcp.server.CommandIdEnum;
import com.collibra.tcp.server.MessageIdEnum;
import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by D-YW44CN on 25/03/2017.
 */
public class ClientSocketConnectionTest extends BaseTest{


    @BeforeClass
    public static void setup() {
        AdjacencyListGraph.reset();
    }

    @Test
    public void testPhase1() throws IOException, InterruptedException {
        final String clientName = "Priya";

        ClientSocketConnection clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        assertTrue("Check that we receive welcome message from the server",
                clientSocketConnection.getMsgFromServer().contains(CommandIdEnum.
                        SERVER_WELCOME_MESSAGE_WITH_PLACEHOLDER.getValue().replace("%s", "").trim()));
        clientSocketConnection.sendToServer("HI, I'M " + clientName);
        assertTrue("Check that the server recognises Hi from client",clientSocketConnection.getMsgFromServer().equals(
                CommandIdEnum.SERVER_INTIAL_RESPONSE.getValue().replace("%s", "").trim() + " " + clientName));
        clientSocketConnection.sendToServer("hi");
        assertEquals("Check that the server fails to recognise this message from client",clientSocketConnection.getMsgFromServer(),
                CommandIdEnum.SERVER_SORRY_MESSAGE.getValue());
        clientSocketConnection.sendToServer(MessageIdEnum.CLIENT_END_MESSAGE.getMessage());
        assertTrue("Check that the server responds with bye message",clientSocketConnection.getMsgFromServer().contains("WE SPOKE FOR"));

    }
}
