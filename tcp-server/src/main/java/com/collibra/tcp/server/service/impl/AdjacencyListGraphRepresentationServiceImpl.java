package com.collibra.tcp.server.service.impl;

import com.collibra.tcp.server.service.NodeService;
import com.collibra.tcp.server.service.Vertex;
import com.collibra.tcp.server.service.utils.DjikshtraShortPath;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class AdjacencyListGraphRepresentationServiceImpl implements NodeService {
    private static final Logger LOGGER = Logger.getLogger(AdjacencyListGraphRepresentationServiceImpl.class.getName());

    public AdjacencyListGraphRepresentationServiceImpl() {
        LOGGER.log(java.util.logging.Level.INFO, "Created an instance of AdjacencyListGraphRepresentationServiceImpl");
    }

    private AdjacencyListGraph adjacencyListGraph = AdjacencyListGraph.getInstance();

    public boolean addNode(String n) {
        return adjacencyListGraph.addVertex(n);
    }

    public Vertex getNode(String n) {
        return adjacencyListGraph.getVertex(n);
    }

    public boolean removeNode(String n) {
        return adjacencyListGraph.removeVertex(n);
    }

    public boolean addEdge(String n1, String n2, Integer weight) {
        return adjacencyListGraph.addEdge(n1, n2, weight);
    }

    public boolean removeEdge(String n1, String n2) {
        return adjacencyListGraph.removeEdge(n1, n2);
    }

    public Collection<Vertex> getAllNodes() {
        return adjacencyListGraph.getAllVertex();
    }

    public Map<Vertex, List<Integer>> getAllEdgesFrom(String n) {
        Vertex vertex = adjacencyListGraph.getVertex(n);
        return vertex == null? Collections.<Vertex, List<Integer>>emptyMap() : vertex.getAllEdges();

    }

    public List<Vertex> getAllEdgesTo(String n) {
        Vertex vertex = adjacencyListGraph.getVertex(n);
        return vertex == null? Collections.<Vertex>emptyList() : vertex.getFromAdjacentVertices();

    }

    public Integer getSHortestPath(String n1, String n2) {
        Vertex v1 = adjacencyListGraph.getVertex(n1);
        Vertex v2 = adjacencyListGraph.getVertex(n2);
        if (v1 == null || v2 == null) return -1;
        return DjikshtraShortPath.getShortestPath(v1, v2, adjacencyListGraph);
    }

    public String closerThan(String n, Integer w) {
        Vertex v1 = adjacencyListGraph.getVertex(n);
        if (v1 == null) return null;
        Map<Vertex, Integer> nodes = DjikshtraShortPath.closerThan(v1, w, adjacencyListGraph);
        StringBuilder nodeList = new StringBuilder();
        boolean isFirst = true;
        for (Map.Entry<Vertex, Integer> entry : nodes.entrySet()) {
            if (entry.getValue() < w && !entry.getKey().getName().equals(n)) {
                if (!isFirst) {
                    nodeList.append(",");
                } else {
                    isFirst = false;
                }
                nodeList.append(entry.getKey().getName());

            }
        }

        return nodeList.toString();
    }


}
