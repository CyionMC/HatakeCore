package net.unix.hatakecore.core.command.basic;

import net.unix.hatakecore.api.command.BukkitCommand;
import net.unix.hatakecore.api.command.Command;
import net.unix.hatakecore.api.command.CommandManager;
import net.unix.hatakecore.core.basic.HatakeCorePlugin;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Unix
 * 14:09, 11.05.2019
 **/
public class CommandManagerImpl implements CommandManager {

    private final CommandMap commandMap;

    public CommandManagerImpl(@NotNull HatakeCorePlugin plugin) {
        this.commandMap = plugin.getServer().getCommandMap();
    }

    @Override
    public void registerCommand(@NotNull Command command) {
        this.commandMap.register(command.getName(), new BukkitCommand(command));
    }

    @Override
    public void registerCommands(@NotNull Command... commands) {
        Arrays.stream(commands).forEachOrdered(this::registerCommand);
    }
}