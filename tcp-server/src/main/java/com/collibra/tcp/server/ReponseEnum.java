package com.collibra.tcp.server;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public enum ReponseEnum {
    NODE_ADDED("NODE ADDED"),

    NODE_ADD_ERROR("ERROR: NODE ALREADY EXISTS"),

    EDGE_ADDED("EDGE ADDED"),

    EDGE_ADD_ERROR("ERROR: NODE NOT FOUND"),

    REMOVE_NODE("NODE REMOVED"),

    REMOVE_NODE_ERROR("ERROR: NODE NOT FOUND"),

    REMOVE_EDGE("EDGE REMOVED"),

    REMOVE_EDGE_ERROR("ERROR: NODE NOT FOUND"),

    SHORTEST_PATH_ERROR("ERROR: NODE NOT FOUND"),

    CLOSER_THAN_ERROR("ERROR: NODE NOT FOUND");

    private String value;
    private ReponseEnum(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
