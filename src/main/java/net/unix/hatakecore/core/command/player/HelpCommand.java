package net.unix.hatakecore.core.command.player;

import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandInfo;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Unix
 * 19:45, 11.05.2019
 **/
@CommandInfo(
        value = "help",
        aliases = { "pomoc" }
)
public class HelpCommand extends Command {

    private final List<String> messages;

    public HelpCommand(@NotNull FileConfiguration configuration) {
        this.messages = ChatHelper.fixColor(configuration.getStringList("helpCommand"));
    }

    @Override
    public void onCommand(Player player, String... args) {
        this.messages.forEach(player::sendMessage);
    }
}