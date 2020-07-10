package com.jonjam.pinboard.common.test.container;

import org.testcontainers.containers.Network;

public class NetworkWrapper {
    private static Network network;

    static {
        network = Network.newNetwork();
    }

    public static Network getNetwork() {
        return network;
    }
}
