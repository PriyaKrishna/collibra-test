package com.collibra.tcp.server.service.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class AdjacencyListGraphRepresentationServiceImplTest {

    @Before
    public void setUp() {
        AdjacencyListGraph.reset();
    }

    @Test
    public void testAddNode() {
        String val = "A1";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val);
        assertEquals(service.getAllNodes().size(), 1);

        service.addNode(val);
        assertEquals("Should remain the same ", service.getAllNodes().size(), 1);

        service.removeNode(val);
        assertEquals(service.getAllNodes().size(), 0);
    }

    @Test
    public void testAddEdgeReturnsFalse() {
        String val1 = "A1";
        String val2 = "A2";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val1);
        assertFalse(service.addEdge(val1, val2, 21));
    }

    @Test
    public void testAddEdgeReturnsTrue() {
        String val1 = "A1";
        String val2 = "A2";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val1);
        service.addNode(val2);
        assertTrue(service.addEdge(val1, val2, 21));
    }

    @Test
    public void testAddMulEdgeReturnsTrue() {
        String val1 = "A1";
        String val2 = "A2";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val1);
        service.addNode(val2);
        assertTrue(service.addEdge(val1, val2, 21));
        assertTrue(service.addEdge(val1, val2, 13));
        assertTrue(service.getAllEdgesFrom(val1).get(service.getNode(val2)).size() == 2);
        assertTrue(service.getAllEdgesTo(val2).size() == 2);

    }

    @Test
    public void testRemoveEdge() {
        String val1 = "V1";
        String val2 = "V2";
        String val3 = "V3";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val1);
        service.addNode(val2);
        service.addNode(val3);
        service.addEdge(val1, val2, 13);
        service.addEdge(val1, val3, 14);
        assertTrue(service.getAllEdgesFrom(val1).size() == 2);
        assertTrue(service.getAllEdgesFrom(val2).size() == 0);
        assertTrue(service.getAllEdgesFrom(val3).size() == 0);
        assertTrue(service.getAllEdgesTo(val2).size() == 1);
        assertTrue(service.getAllEdgesTo(val3).size() == 1);

        service.removeEdge(val1, val2);
        assertTrue(service.getAllEdgesFrom(val1).size() == 1);
        assertTrue(service.getAllEdgesFrom(val2).size() == 0);
        assertTrue(service.getAllEdgesFrom(val3).size() == 0);
        assertTrue(service.getAllEdgesTo(val2).size() == 0);
        assertTrue(service.getAllEdgesTo(val3).size() == 1);

        service.removeEdge(val1, val3);
        assertTrue(service.getAllEdgesFrom(val1).size() == 0);
        assertTrue(service.getAllEdgesFrom(val2).size() == 0);
        assertTrue(service.getAllEdgesFrom(val3).size() == 0);
        assertTrue(service.getAllEdgesTo(val2).size() == 0);
        assertTrue(service.getAllEdgesTo(val3).size() == 0);
    }

    @Test
    public void testRemoveNode() {
        String val1 = "V1";
        String val2 = "V2";
        String val3 = "V3";
        AdjacencyListGraphRepresentationServiceImpl service = new AdjacencyListGraphRepresentationServiceImpl();
        service.addNode(val1);
        service.addNode(val2);
        service.addNode(val3);
        service.addEdge("V1", val2, 13);
        service.addEdge(val1, val3, 14);
        assertTrue(service.getAllEdgesFrom(val1).size() == 2);
        assertTrue(service.getAllEdgesFrom(val2).size() == 0);
        assertTrue(service.getAllEdgesFrom(val3).size() == 0);
        assertTrue(service.getAllEdgesTo(val2).size() == 1);
        assertTrue(service.getAllEdgesTo(val3).size() == 1);

        assertTrue(service.removeNode(val1));
        assertTrue(service.getAllEdgesFrom(val1).size() == 0);
        assertTrue(service.getAllEdgesFrom(val2).size() == 0);
        assertTrue(service.getAllEdgesFrom(val3).size() == 0);
        assertTrue(service.getAllEdgesTo(val2).size() == 0);
        assertTrue(service.getAllEdgesTo(val3).size() == 0);

    }
}
