package net.rankedproject.gameapi.player.type;

import com.google.common.base.Preconditions;
import net.rankedproject.gameapi.player.GamePlayerState;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerWaitingState implements GamePlayerState {

    @Override
    public void apply(@NotNull Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setLevel(0);
        player.setTotalExperience(0);
        player.setFireTicks(0);
        player.setVisualFire(false);
        player.setGameMode(GameMode.ADVENTURE);

        var maxHealthAttribute = player.getAttribute(Attribute.MAX_HEALTH);
        Preconditions.checkNotNull(maxHealthAttribute);

        var maxHealth = maxHealthAttribute.getDefaultValue();
        player.setHealth(maxHealth);
    }
}
