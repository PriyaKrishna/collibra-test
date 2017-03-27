package com.collibra.tcp.server;

import com.collibra.tcp.server.service.NodeService;
import com.collibra.tcp.server.service.NodeServiceFactory;

import java.io.IOException;

/**
 * Created by d-yw44cn on 24/03/2017.
 */
public enum CommandIdEnum {
    SERVER_WELCOME_MESSAGE_WITH_PLACEHOLDER("HI, I'M %s") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            con.write(String.format(getValue(), con.getSessionId().toString()));
        }
    },
    SERVER_INTIAL_RESPONSE("HI %s") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            con.write(String.format(getValue(), con.getClientName()));
        }
    },
    SERVER_BYE_MESSAGE("BYE %s, WE SPOKE FOR %d MS") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            con.close();
        }
    },
    SERVER_SORRY_MESSAGE("SORRY, I DIDN'T UNDERSTAND THAT") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            con.write(getValue());
        }
    },

    ADD_NODE("ADD NODE") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String node = input.substring(getValue().length(), input.length());
            if(getCommandExecutionService().addNode(node.trim())) {
                con.write(ReponseEnum.NODE_ADDED.getValue());
            } else {
                con.write(ReponseEnum.NODE_ADD_ERROR.getValue());
            }
        }
    },

    ADD_EDGE("ADD EDGE") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String[] values = input.substring(getValue().length() + 1, input.length()).split(" ");
            if (getCommandExecutionService().addEdge(values[0].trim(), values[1].trim(), Integer.valueOf(values[2].trim()))) {
                con.write(ReponseEnum.EDGE_ADDED.getValue());
            } else {
                con.write(ReponseEnum.EDGE_ADD_ERROR.getValue());
            }
        }
    },

    REMOVE_NODE("REMOVE NODE") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String node = input.substring(getValue().length(), input.length());
            if (getCommandExecutionService().removeNode(node.trim())) {
                con.write(ReponseEnum.REMOVE_NODE.getValue());
            } else {
                con.write(ReponseEnum.REMOVE_NODE_ERROR.getValue());
            }
        }
    },

    REMOVE_EDGE("REMOVE EDGE") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String[] values = input.substring(getValue().length() + 1, input.length()).split(" ");
            if (getCommandExecutionService().removeEdge(values[0].trim(), values[1].trim())) {
                con.write(ReponseEnum.REMOVE_EDGE.getValue());
            } else {
                con.write(ReponseEnum.REMOVE_EDGE_ERROR.getValue());
            }
        }
    },

    SHORTEST_PATH("SHORTEST PATH") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String[] values = input.substring(getValue().length() + 1, input.length()).split(" ");
            Integer shortestPath = getCommandExecutionService().getSHortestPath(values[0].trim(), values[1].trim());
            if (shortestPath.equals(-1)) {
                con.write(ReponseEnum.SHORTEST_PATH_ERROR.getValue());
            } else {
                con.write(shortestPath.toString());
            }
        }
    },

    CLOSER_THAN("CLOSER THAN") {
        @Override
        public void process(String input, ServerSocketConnection con) throws IOException, InterruptedException {
            String[] values = input.substring(getValue().length() + 1, input.length()).split(" ");
            String nodeList = getCommandExecutionService().closerThan(values[1].trim(), Integer.valueOf(values[0].trim()));
            if (nodeList == null) {
                con.write(ReponseEnum.CLOSER_THAN_ERROR.getValue());
            } else {
                con.write(nodeList);
            }
        }
    };

    private String value;
    private static final NodeService commandExecutionService = NodeServiceFactory.getService();

    CommandIdEnum(String input) {
        value = input;
    }

    public String getValue() {
        return value;
    }

    public NodeService getCommandExecutionService() {
        return commandExecutionService;
    }

    public abstract void  process(String input, ServerSocketConnection con)
            throws IOException, InterruptedException;

    public static CommandIdEnum checkAndGetNodeCommand(String input, ServerSocketConnection con)
            throws IOException, InterruptedException {
        for (CommandIdEnum command : values()) {
            if (input.contains(command.getValue())) {
                return command;
            }
        }
        return null;
    }
}
