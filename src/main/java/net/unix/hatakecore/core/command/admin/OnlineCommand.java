package net.unix.hatakecore.core.command.admin;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Unix on 05.10.2019.
 */
@CommandInfo(
        value = "online",
        permission = "core.online"
)
public class OnlineCommand extends Command {
    @Override
    public void onCommand(Player player, String... args) {
        ChatHelper.sendMessage(player, "&8>> &aIlosc graczy online wynosi: &6" + Bukkit.getOnlinePlayers().size() + " &a.");
    }
}