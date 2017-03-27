package com.collibra.tcp.client;

import com.collibra.tcp.server.MessageIdEnum;
import com.collibra.tcp.server.ReponseEnum;
import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by D-YW44CN on 25/03/2017.
 */
public class NodeTest extends BaseTest{
    @BeforeClass
    public static void setup() {
        AdjacencyListGraph.reset();
    }

    @Test
    public void testPhase2AddNode() throws IOException, InterruptedException {
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE A");
        assertEquals("Adding a new node failed", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.NODE_ADDED.getValue());
        clientSocketConnection.sendToServer(MessageIdEnum.CLIENT_END_MESSAGE.getMessage());
        clientSocketConnection.getMsgFromServer();

        //create new client, add an existing node
        clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE A");
        assertEquals("Adding existing node failed ", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.NODE_ADD_ERROR.getValue());

        //create yet another client, and add a new node
        clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("ADD NODE B");
        assertEquals(clientSocketConnection.getMsgFromServer(),
                ReponseEnum.NODE_ADDED.getValue());

        //create another client to remove a node
        clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();
        clientSocketConnection.sendToServer("REMOVE NODE B");
        assertEquals("Removing a node failed", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.REMOVE_NODE.getValue());
        //add more nodes
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
