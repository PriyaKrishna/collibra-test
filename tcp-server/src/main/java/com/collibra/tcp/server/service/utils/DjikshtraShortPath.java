package com.collibra.tcp.server.service.utils;

import com.collibra.tcp.server.service.Vertex;
import com.collibra.tcp.server.service.impl.AdjacencyListGraph;

import java.util.*;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class DjikshtraShortPath {
    public static Integer getShortestPath(Vertex v1, Vertex v2, AdjacencyListGraph graph) {
        Collection<Vertex> allVertex = new ArrayList<Vertex>();
        allVertex.addAll(graph.getAllVertex());
        BinaryMinHeap<Vertex> binaryMinHeap = new BinaryMinHeap<Vertex>();
        Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();
        for (Vertex vertex : allVertex) {
            binaryMinHeap.add(vertex, Integer.MAX_VALUE);
        }

        binaryMinHeap.decrease(v1, 0);
        distance.put(v1, 0);

        while(!binaryMinHeap.isEmpty()) {
            BinaryMinHeap<Vertex>.Node heapNode = binaryMinHeap.extractMinNode();
            Vertex current = heapNode.key;
            if (current.equals(v2)) {
                return heapNode.weight;
            }

            distance.put(current, heapNode.weight);
            for (Map.Entry<Vertex, List<Integer>> edge : current.getAllEdges().entrySet()) {
                Vertex adjacentVertex = edge.getKey();
                if (!binaryMinHeap.contains(adjacentVertex)) {
                    continue;
                }

                int newD;
                if (distance.get(current) == Integer.MAX_VALUE) {
                    newD = Integer.MAX_VALUE;
                } else {
                    newD = distance.get(current) + getMin(edge.getValue());
                }
                if (binaryMinHeap.getWeight(adjacentVertex) > newD) {
                    binaryMinHeap.decrease(adjacentVertex, newD);
                }
            }
        }
        return null;
    }

    private static Integer getMin(List<Integer> value) {
        Integer min = Integer.MAX_VALUE;
        for (Integer val : value) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }


    public static Map<Vertex, Integer> closerThan(Vertex v1, Integer w, AdjacencyListGraph graph) {
        Collection<Vertex> allVertex = new ArrayList<Vertex>();
        allVertex.addAll(graph.getAllVertex());
        BinaryMinHeap<Vertex> binaryMinHeap = new BinaryMinHeap<Vertex>();
        Map<Vertex, Integer> distance = new TreeMap<Vertex, Integer>(new Comparator<Vertex>() {
            public int compare(Vertex o1, Vertex o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Vertex vertex : allVertex) {
            binaryMinHeap.add(vertex, Integer.MAX_VALUE);
        }

        binaryMinHeap.decrease(v1, 0);
        distance.put(v1, 0);

        while(!binaryMinHeap.isEmpty()) {
            BinaryMinHeap<Vertex>.Node heapNode = binaryMinHeap.extractMinNode();
            Vertex current = heapNode.key;
            distance.put(current, heapNode.weight);
            for (Map.Entry<Vertex, List<Integer>> edge : current.getAllEdges().entrySet()) {
                Vertex adjacentVertex = edge.getKey();
                if (!binaryMinHeap.contains(adjacentVertex)) {
                    continue;
                }

                int newD;
                if (distance.get(current) == Integer.MAX_VALUE) {
                    newD = Integer.MAX_VALUE;
                } else {
                    newD = distance.get(current) + getMin(edge.getValue());
                }
                if (binaryMinHeap.getWeight(adjacentVertex) > newD) {
                    binaryMinHeap.decrease(adjacentVertex, newD);
                }
            }
        }
        return distance;
    }
}
