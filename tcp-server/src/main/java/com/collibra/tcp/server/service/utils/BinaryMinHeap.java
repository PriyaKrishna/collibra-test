package com.collibra.tcp.server.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class BinaryMinHeap<T> {
    private List<Node> nodes = new ArrayList<Node>();
    private Map<T, Integer> nodePosition = new HashMap<T, Integer>();

    protected class Node {
        T key;
        Integer weight;
    }

    public boolean contains(T key) {
        return (nodePosition.containsKey(key));
    }

    public Integer getWeight(T key) {
        Integer position = nodePosition.get(key);
        if( position == null ) {
            return null;
        } else {
            return nodes.get(position).weight;
        }
    }

    public void add(T key, Integer weight) {
        Node node = new Node();
        node.key = key;
        node.weight = weight;

        nodes.add(node);
        int size = nodes.size() - 1;
        nodePosition.put(key, size);
        int parent = (size - 1)/2;
        int current = size;
        while (parent >= 0) {
            Node parentNode = nodes.get(parent);
            Node currentNode = nodes.get(current);
            if (parentNode.weight > currentNode.weight) {
                swap(parentNode, currentNode);
                updateMapPosition(parentNode.key, currentNode.key, parent, current);
                current = parent;
                parent = (current -1)/2;
            } else {
                break;
            }
        }
    }

    public T min() {
        return nodes.get(0).key;
    }

    public void decrease(T key, Integer weight) {
        Integer position = nodePosition.get(key);
        if (position != null) {

            int current = position;
            int parent = (position - 1)/2;
            Node currentNode = nodes.get(position);
            currentNode.weight = weight;
            while (parent >= 0) {
                currentNode = nodes.get(current);
                Node parentNode = nodes.get(parent);
                if (parentNode.weight > currentNode.weight) {
                    swap(parentNode, currentNode);
                    updateMapPosition(parentNode.key, currentNode.key, parent, current);
                    current = parent;
                    parent = (current -1)/2;
                } else {
                    break;
                }

            }
        }
    }

    public T extractMin() {
        if (nodes.size() == 0) return null;
        Node minNode = extractMinNode();
        return minNode.key;
    }

    public boolean isEmpty() {
        return nodes.size() == 0;
    }

    public Node extractMinNode() {
        Node minNode = new Node();
        minNode.key = nodes.get(0).key;
        minNode.weight = nodes.get(0).weight;
        int size = nodes.size() -1;
        Node lastNode = nodes.get(size);
        swap(nodes.get(0), lastNode);
        nodes.remove(size);
        nodePosition.remove(minNode.key);
        if (nodes.size() == 0) {
            return minNode;
        }
        nodePosition.put(nodes.get(0).key, 0);
        Node currentNode = nodes.get(0);
        int current = 0;
        size--;
        while (true) {
            int left = 2*current + 1;
            int right = 2*current + 2;
            if (left > size) {
                break;
            }
            if (right > size) {
                right = left;
            }

            int smaller = nodes.get(left).weight < nodes.get(right).weight? left: right;
            if(nodes.get(current).weight > nodes.get(smaller).weight){
                swap(nodes.get(current), nodes.get(smaller));
                updateMapPosition(nodes.get(current).key,nodes.get(smaller).key,current,smaller);
                current = smaller;
            }else{
                break;
            }
        }

        return minNode;
    }


    private void updateMapPosition(T parentKey, T currentKey, int parent, int current) {
        nodePosition.put(parentKey, parent);
        nodePosition.put(currentKey, current);
    }

    private void swap(Node node1, Node node2) {
        Node temp = new Node();
        temp.weight = node1.weight;
        temp.key = node1.key;

        node1.weight = node2.weight;
        node1.key = node2.key;

        node2.weight = temp.weight;
        node2.key = temp.key;
    }
}
