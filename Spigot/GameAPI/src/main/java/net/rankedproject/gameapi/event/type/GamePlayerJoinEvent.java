package net.rankedproject.gameapi.event.type;

import lombok.Getter;
import net.rankedproject.gameapi.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class GamePlayerJoinEvent extends PlayerEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Game game;

    public GamePlayerJoinEvent(@NotNull Game game, @NotNull Player who) {
        super(who);
        this.game = game;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
