package com.collibra.tcp.server.utils;

import com.collibra.tcp.server.service.utils.BinaryMinHeap;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class BinaryMinHeapTest {

    @Test
    public void testGetMin() {
        BinaryMinHeap<String> binaryMinHeap = new BinaryMinHeap<String>();
        binaryMinHeap.add("A1", 20);
        binaryMinHeap.add("A2", 21);
        binaryMinHeap.add("A3", 0);
        assertTrue(binaryMinHeap.min().equals("A3"));

        binaryMinHeap.add("A4", 17);
        binaryMinHeap.add("A5", 54);
        assertTrue(binaryMinHeap.min().equals("A3"));
    }


    @Test
    public void testExtractMin() {
        BinaryMinHeap<String> binaryMinHeap = new BinaryMinHeap<String>();
        binaryMinHeap.add("A1", 20);
        binaryMinHeap.add("A2", 21);
        binaryMinHeap.add("A3", 0);
        assertTrue(binaryMinHeap.extractMin().equals("A3"));

        binaryMinHeap.add("A4", 17);
        binaryMinHeap.add("A5", 54);
        assertTrue(binaryMinHeap.extractMin().equals("A4"));
        assertTrue(binaryMinHeap.extractMin().equals("A1"));
        assertTrue(binaryMinHeap.extractMin().equals("A2"));
        assertTrue(binaryMinHeap.extractMin().equals("A5"));
        assertTrue(binaryMinHeap.extractMin() == null);
    }

    @Test
    public void testDecrease() {
        BinaryMinHeap<String> binaryMinHeap = new BinaryMinHeap<String>();
        binaryMinHeap.add("A1", 20);
        binaryMinHeap.add("A2", 21);
        binaryMinHeap.add("A3", 17);
        binaryMinHeap.decrease("A1", 16);
        assertTrue(binaryMinHeap.min().equals("A1"));
        assertTrue(binaryMinHeap.extractMin().equals("A1"));
        binaryMinHeap.decrease("A2", 15);
        assertTrue(binaryMinHeap.min().equals("A2"));
        assertTrue(binaryMinHeap.extractMin().equals("A2"));
        binaryMinHeap.decrease("A3", 3);
        assertTrue(binaryMinHeap.min().equals("A3"));
        assertTrue(binaryMinHeap.extractMin().equals("A3"));
        binaryMinHeap.decrease("A1", 4);

    }
}
