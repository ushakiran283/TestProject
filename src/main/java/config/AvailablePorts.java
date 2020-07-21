// Copyright (c) 2017 Peel Technologies Inc. All Rights Reserved.
package config;

import java.net.ServerSocket;

public class AvailablePorts {

    public String getPort() throws Exception {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        String port = Integer.toString(socket.getLocalPort());
        socket.close();
        return port;
    }

    public static void main(String[] args) throws Exception {
        AvailablePorts ap = new AvailablePorts();
        for (int i = 0; i < 100; i++) {
            System.out.println(ap.getPort());
        }
    }
}
