package net.rankedproject.gameapi.mechanic;

import java.util.IdentityHashMap;
import java.util.Map;

public class GameMechanicContext {

    private final Map<Class<? extends GameMechanic>, GameMechanic> enabledMechanics = new IdentityHashMap<>();

    public void enable(GameMechanic mechanic) {
        enabledMechanics.put(mechanic.getClass(), mechanic);
        mechanic.enable();
    }

    public void disable(Class<? extends GameMechanic> mechanicType) {
        GameMechanic mechanic = enabledMechanics.remove(mechanicType);
        mechanic.disable();
    }

    public void disableAll() {
        var mechanics = enabledMechanics.values();
        mechanics.forEach(GameMechanic::disable);

        enabledMechanics.clear();
    }
}