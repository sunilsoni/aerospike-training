package com.training.aerospike.repo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ConnectionInfo implements Serializable {

    public static final int DEFAULT_PORT = 3000;

    private String[] hostnames;
    private int[] ports;
    private String username;
    private String password;
    private String namespace;

    private ConnectionInfo(){

    }

    public ConnectionInfo(String hostWithPort, String username, String password, String namespace) {
        String[] hostArr = hostWithPort.split(",");

        hostnames = new String[hostArr.length];
        ports = new int[hostArr.length];

        for (int i = 0; i < hostArr.length; i++) {
            String host = hostArr[i];
            if (host.contains(":")) {
                String[] hostArr1 = host.split(":");
                if (hostArr1.length == 2) {
                    hostnames[i] = hostArr1[0];
                    ports[i] = Integer.parseInt(hostArr1[i]);
                }
            } else {
                hostnames[i] = host;
                ports[i] = DEFAULT_PORT;
            }
        }

        setHostnames(hostnames);
        setPorts(ports);
        setNamespace(namespace);

        validate();
    }

    public void validate()  {

        if(hostnames==null || hostnames.length==0){
            throw new IllegalArgumentException("hostnames can not be null !");
        }

        if(ports==null || ports.length==0){
            throw new IllegalArgumentException("ports can not be null !");
        }

        if(namespace==null || namespace.isEmpty()){
            throw new IllegalArgumentException("namespace can not be null !");
        }
    }
}
