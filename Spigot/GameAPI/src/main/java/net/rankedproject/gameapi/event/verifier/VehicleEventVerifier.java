package net.rankedproject.gameapi.event.verifier;

import net.rankedproject.gameapi.Game;
import org.bukkit.event.vehicle.VehicleEvent;

public class VehicleEventVerifier implements GameEventVerifier<VehicleEvent> {

    @Override
    public boolean isGameEvent(VehicleEvent event, Game game) {
        var worldContext = game.getWorldContext();
        var world = worldContext.getWorld();
        if (world == null) return false;

        var vehicle = event.getVehicle();
        var entityWorld = vehicle.getWorld();

        return world.getName().equals(entityWorld.getName());
    }

    @Override
    public Class<? extends VehicleEvent> getEventType() {
        return VehicleEvent.class;
    }
}