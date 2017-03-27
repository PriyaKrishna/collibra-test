package com.collibra.tcp.client;

import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by D-YW44CN on 27/03/2017.
 */
public class CloserThanTest extends BaseTest {
    @BeforeClass
    public static void setup() {
        AdjacencyListGraph.reset();
    }

    @Test
    public void testCloserThan() throws IOException {
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection("localhost", 50000);
        clientSocketConnection.getMsgFromServer();

        addNodes(clientSocketConnection);
        addEdges(clientSocketConnection);
        clientSocketConnection.sendToServer("CLOSER THAN 10 A");
        assertEquals("Getting closer than path from A failed", clientSocketConnection.getMsgFromServer(),"B,C,D,E,F");
    }
}
