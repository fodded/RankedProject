package net.rankedproject.lobby.listener;

import net.rankedproject.common.data.domain.RankedPlayer;
import net.rankedproject.spigot.data.PlayerSession;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RankedPlayer rankedPlayer = PlayerSession.get(player.getUniqueId(), RankedPlayer.class);
        if (rankedPlayer == null) {
            player.sendRichMessage("<red>Ваши данные не были загружены!");
        } else {
            player.sendRichMessage("<green>Ваши данные были успешно загружены! " + rankedPlayer);
        }
    }
}
