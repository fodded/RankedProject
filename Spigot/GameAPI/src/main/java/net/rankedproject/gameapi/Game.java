package net.rankedproject.gameapi;

import net.rankedproject.gameapi.state.GameState;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Game {

    private final Collection<? super GameState> states = new ArrayList<>();

    private void initStates() {

    }

    protected abstract Collection<? extends GameState> getGameState();
}