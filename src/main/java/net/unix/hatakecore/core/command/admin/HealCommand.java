package net.unix.hatakecore.core.command.admin;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.entity.Player;

/**
 * @author Unix on 05.10.2019.
 */
@CommandInfo(
        value = "heal",
        permission = "core.heal",
        aliases = { "ulecz" }
)
public class HealCommand extends Command {
    @Override
    public void onCommand(Player player, String... args) {
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));

        ChatHelper.sendMessage(player, "&8>> &aZostales uleczony!");
    }
}