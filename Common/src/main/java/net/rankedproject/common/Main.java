package net.rankedproject.common;

import net.rankedproject.common.rest.impl.RankedPlayerRestClient;
import net.rankedproject.common.rest.provider.RestProvider;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        RestProvider.get(RankedPlayerRestClient.class)
                .getPlayerAsync(UUID.fromString("c2d3d415-dbdd-4d1a-92a5-33badd11d4be"))
                .thenAccept(rankedPlayer -> {
                    System.out.println(rankedPlayer.getId() + " was retrieved " + rankedPlayer.toString());
                    rankedPlayer.setDeaths(1000);
                })
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
                });

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
