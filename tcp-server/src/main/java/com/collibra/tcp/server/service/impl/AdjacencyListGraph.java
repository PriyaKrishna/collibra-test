package com.collibra.tcp.server.service.impl;

import com.collibra.tcp.server.service.Vertex;

import java.util.*;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class AdjacencyListGraph {
    private static AdjacencyListGraph instance = new AdjacencyListGraph();

    private static Map<String, Vertex> allVertex ;

    private AdjacencyListGraph() {
        allVertex = new HashMap<String, Vertex>();
    }

    public static synchronized void reset() {
        allVertex = new HashMap<String, Vertex>();
    }

    public static AdjacencyListGraph getInstance() {
        return instance;
    }

    public synchronized Vertex getVertex(String name) {
        return allVertex.get(name);
    }

    public synchronized boolean addVertex(String name) {
        if (allVertex.containsKey(name)) {
            return false;
        } else {
            Vertex vertex = new Vertex();
            vertex.setName(name);
            allVertex.put(name, vertex);
            return true;
        }
    }

    public synchronized boolean removeVertex(String n) {
        if (allVertex.containsKey(n)) {
            Vertex vertex = allVertex.get(n);
            for (Vertex toVertex : vertex.getToAdjacentVertices()) {
                toVertex.getFromAdjacentVertices().remove(vertex);
            }
            allVertex.remove(n);
            return true;
        }
        return false;
    }

    public synchronized boolean removeEdge(String n1, String n2) {
        if (allVertex.containsKey(n1) && allVertex.containsKey(n2)) {
            allVertex.get(n1).getToAdjacentVertices().remove(allVertex.get(n2));
            allVertex.get(n2).getFromAdjacentVertices().remove(allVertex.get(n1));
            return true;
        }
        return false;
    }

    public synchronized boolean addEdge(String n1, String n2, Integer weight) {
        Vertex v1;
        Vertex v2;

        if (allVertex.containsKey(n1)) {
            v1 = allVertex.get(n1);
        } else {
            return false;
        }

        if (allVertex.containsKey(n2)) {
            v2 = allVertex.get(n2);
        } else {
            return false;
        }
        v1.addToAdjacentVertex(v2, weight);
        v2.addFromAdjacentVertex(v1);

        return true;
    }

    public synchronized Collection<Vertex> getAllVertex() {
        return allVertex.values();
    }
}
