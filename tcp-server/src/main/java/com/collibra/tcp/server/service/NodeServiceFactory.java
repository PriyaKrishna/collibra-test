package com.collibra.tcp.server.service;

import com.collibra.tcp.server.service.impl.AdjacencyListGraphRepresentationServiceImpl;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public class NodeServiceFactory {
    public static NodeService getService() {
        return new AdjacencyListGraphRepresentationServiceImpl();
    }
}
