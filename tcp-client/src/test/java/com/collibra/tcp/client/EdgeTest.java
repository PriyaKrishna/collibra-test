package com.collibra.tcp.client;

import com.collibra.tcp.server.ReponseEnum;
import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by D-YW44CN on 25/03/2017.
 */
public class EdgeTest  extends BaseTest {
    @BeforeClass
    public static void setup() {
        AdjacencyListGraph.reset();
    }

    @Test
    public void testPhase2AddEdge() throws IOException, InterruptedException {
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();
        addNodes(clientSocketConnection);
        clientSocketConnection.sendToServer("ADD EDGE A B 5");
        assertEquals("Adding a new edge failed", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.EDGE_ADDED.getValue());
        //add a existing edge
        clientSocketConnection.sendToServer("ADD EDGE A B 6");
        assertEquals("Adding existing edge failed ", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.EDGE_ADDED.getValue());

        //add an edge with a non-existing node
        clientSocketConnection.sendToServer("ADD EDGE A Z 6");
        assertEquals("Adding edge with non existing node failed ", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.EDGE_ADD_ERROR.getValue());

        //remove an edge
        clientSocketConnection.sendToServer("REMOVE EDGE A B");
        assertEquals("Removing existing edge failed ", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.REMOVE_EDGE.getValue());


        //remove a non-existing edge
        clientSocketConnection.sendToServer("REMOVE EDGE A Z");
        assertEquals("Removing non-existing edge failed ", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.REMOVE_EDGE_ERROR.getValue());

        //add more edges
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


}
