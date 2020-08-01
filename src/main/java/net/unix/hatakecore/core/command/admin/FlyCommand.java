package net.unix.hatakecore.core.command.admin;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import org.bukkit.entity.Player;

/**
 * @author Unix on 06.10.2019.
 */
@CommandInfo(
        value = "fly",
        permission = "core.fly"
)
public class FlyCommand extends Command {
    @Override
    public void onCommand(Player player, String... args) {
    }
}