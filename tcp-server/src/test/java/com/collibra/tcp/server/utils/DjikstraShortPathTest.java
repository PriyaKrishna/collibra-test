package com.collibra.tcp.server.utils;

import com.collibra.tcp.server.service.impl.AdjacencyListGraph;
import com.collibra.tcp.server.service.utils.DjikshtraShortPath;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class DjikstraShortPathTest {

    @Test
    public void testGetShortestPath() {
        DjikshtraShortPath djikstraShortPath = new DjikshtraShortPath();
        AdjacencyListGraph graph = AdjacencyListGraph.getInstance();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "E", 2);
        graph.addEdge("A", "D", 9);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 3);
        graph.addEdge("F", "D", 2);
        graph.addEdge("E", "F", 3);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("A"),
                graph.getVertex("B"), graph) == 5);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("A"),
                graph.getVertex("C"), graph) == 7);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("A"),
                graph.getVertex("D"), graph) == 7);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("A"),
                graph.getVertex("E"), graph) == 2);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("A"),
                graph.getVertex("F"), graph) == 5);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("B"),
                graph.getVertex("A"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("B"),
                graph.getVertex("C"), graph) == 2);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("B"),
                graph.getVertex("D"), graph) == 5);
        assertEquals(djikstraShortPath.getShortestPath(graph.getVertex("B"),
                graph.getVertex("F"), graph).longValue(),Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("B"),
                graph.getVertex("E"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("C"),
                graph.getVertex("D"), graph) == 3);
        assertEquals(djikstraShortPath.getShortestPath(graph.getVertex("C"),
                graph.getVertex("F"), graph).longValue(),Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("C"),
                graph.getVertex("E"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("C"),
                graph.getVertex("A"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("D"),
                graph.getVertex("B"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("D"),
                graph.getVertex("C"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("D"),
                graph.getVertex("E"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("D"),
                graph.getVertex("F"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("D"),
                graph.getVertex("A"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("F"),
                graph.getVertex("B"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("F"),
                graph.getVertex("C"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("F"),
                graph.getVertex("E"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("F"),
                graph.getVertex("A"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("F"),
                graph.getVertex("D"), graph) == 2);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("E"),
                graph.getVertex("B"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("E"),
                graph.getVertex("C"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("E"),
                graph.getVertex("F"), graph) == 3);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("E"),
                graph.getVertex("A"), graph) == Integer.MAX_VALUE);
        assertTrue(djikstraShortPath.getShortestPath(graph.getVertex("E"),
                graph.getVertex("D"), graph) == 5);
    }

}
