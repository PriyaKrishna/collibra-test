package com.collibra.tcp.client;

import com.collibra.tcp.server.ReponseEnum;
import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by D-YW44CN on 25/03/2017.
 */
public class ShortestPathTest extends BaseTest {
    @BeforeClass
    public static void setup() {
        AdjacencyListGraph.reset();
    }



    @Test
    public void testGetShortestPath() throws IOException {
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();

        addNodes(clientSocketConnection);
        addEdges(clientSocketConnection);
        clientSocketConnection.sendToServer("SHORTEST PATH A B");
        assertEquals("Getting path from A to B failed", clientSocketConnection.getMsgFromServer(),"5");

        clientSocketConnection.sendToServer("SHORTEST PATH A Z");
        assertEquals("Getting path from A to Z failed", clientSocketConnection.getMsgFromServer(),
                ReponseEnum.SHORTEST_PATH_ERROR.getValue());
    }


}
