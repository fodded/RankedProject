package net.rankedproject.gameapi.event.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.rankedproject.gameapi.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class GameStartEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Game game;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
