package net.unix.hatakecore.core.command.player;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Unix on 05.10.2019.
 */
@CommandInfo("vip")
public class VipCommand extends Command {

    private final List<String> messages;

    public VipCommand(@NotNull FileConfiguration configuration) {
        this.messages = ChatHelper.fixColor(configuration.getStringList("vipCommand"));
    }

    @Override
    public void onCommand(Player player, String... args) {
        this.messages.forEach(player::sendMessage);
    }
}