package com.collibra.tcp.server.service;


import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public interface NodeService {
    boolean addNode(String n);

    Vertex getNode(String n);

    boolean removeNode(String n);

    boolean addEdge(String n1, String n2, Integer weight);

    boolean removeEdge(String n1, String n2);

    Collection<Vertex> getAllNodes();

    public Map<Vertex, List<Integer>> getAllEdgesFrom(String n);

    List<Vertex> getAllEdgesTo(String n);

    Integer getSHortestPath(String n1, String n2);

    String closerThan(String n, Integer weight);


}
