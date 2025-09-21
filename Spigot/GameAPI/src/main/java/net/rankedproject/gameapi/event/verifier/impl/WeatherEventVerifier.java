package net.rankedproject.gameapi.event.verifier.impl;

import net.rankedproject.gameapi.Game;
import net.rankedproject.gameapi.event.verifier.GameEventVerifier;
import org.bukkit.event.weather.WeatherEvent;

public class WeatherEventVerifier implements GameEventVerifier<WeatherEvent> {

    @Override
    public boolean isGameEvent(WeatherEvent event, Game game) {
        var worldContext = game.getWorldContext();
        var world = worldContext.getWorld();

        if (world == null) return false;
        return world.getName().equals(event.getWorld().getName());
    }

    @Override
    public Class<? extends WeatherEvent> getEventType() {
        return WeatherEvent.class;
    }
}