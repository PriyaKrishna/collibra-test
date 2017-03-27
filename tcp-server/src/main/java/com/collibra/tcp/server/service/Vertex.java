package com.collibra.tcp.server.service;

import java.util.*;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class Vertex {
    String name;
    Integer weight;
    Map<Vertex, List<Integer>> toAdjacentVertices = new HashMap<Vertex, List<Integer>>();
    List<Vertex> fromAdjacentVertices = new ArrayList<Vertex>();

    public void addToAdjacentVertex(Vertex v, Integer w) {
        List<Integer> weights = toAdjacentVertices.get(v);
        if (weights == null) {
            weights = new ArrayList<Integer>();
        }
        weights.add(w);
        toAdjacentVertices.put(v, weights);
    }

    public void addFromAdjacentVertex(Vertex v) {
        fromAdjacentVertices.add(v);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Collection<Vertex> getToAdjacentVertices() {
        return toAdjacentVertices.keySet();
    }

    public Map<Vertex, List<Integer>> getAllEdges() {
        return toAdjacentVertices;
    }
    public List<Vertex> getFromAdjacentVertices() {
        return fromAdjacentVertices;
    }

    public void setFromAdjacentVertices(List<Vertex> fromAdjacentVertices) {
        fromAdjacentVertices = fromAdjacentVertices;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (!name.equals(other.name))
            return false;
        return true;
    }
}
