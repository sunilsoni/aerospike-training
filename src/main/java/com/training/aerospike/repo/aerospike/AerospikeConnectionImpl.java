package com.training.aerospike.repo.aerospike;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.cdt.MapPolicy;
import com.aerospike.client.policy.BatchPolicy;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.ConsistencyLevel;
import com.aerospike.client.policy.WritePolicy;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.ConnectionInfo;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

public class AerospikeConnectionImpl extends Connection<IAerospikeClient> {
    private IAerospikeClient client;

    private ClientPolicy clientPolicy;
    private WritePolicy wPolicy;
    private BatchPolicy batchPolicy;
    private MapPolicy mPolicy;
    private Set<Long> dateList;

    public AerospikeConnectionImpl(ConnectionInfo info) {
        super(info);

        this.clientPolicy = new ClientPolicy();
        this.wPolicy = new WritePolicy();
        clientPolicy.readPolicyDefault.consistencyLevel=ConsistencyLevel.CONSISTENCY_ALL;

        if (!StringUtils.isEmpty(info.getUsername())) {
            clientPolicy.user = info.getUsername();
            clientPolicy.password = info.getPassword();
        }
        client = new AerospikeClient(clientPolicy, getHosts(info));

    }

    public AerospikeConnectionImpl(ConnectionInfo info, IAerospikeClient client) {
        super(info);
        this.client=client;
    }

    private Host[] getHosts(ConnectionInfo info) {
        String[] hostNames = info.getHostnames();
        int[] ports = info.getPorts();
        Host[] hosts = new Host[hostNames.length];

        for (int i = 0; i < hostNames.length; i++) {
            hosts[i] = new Host(hostNames[i], ports[i]);
        }

        return hosts;
    }

    @Override
    public IAerospikeClient openSession() {
        return client;
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
