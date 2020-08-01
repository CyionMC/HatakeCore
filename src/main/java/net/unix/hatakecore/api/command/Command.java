package net.unix.hatakecore.api.command;

import lombok.Getter;
import net.unix.hatakecore.api.helper.ChatHelper;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * @author Unix
 * 14:08, 11.05.2019
 **/
@Getter
public abstract class Command {

    private final String       name;
    private final String       usage;
    private final String       permission;
    private final List<String> aliases;

    protected Command() {
        final CommandInfo commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);

        if (commandInfo == null) {
            throw new CommandNotFoundException("Nie można zaladować komendy \"" + this.getClass().getSimpleName() + "\", ponieważ nie posiada ona adnotacji \"CommandInfo\"");
        }

        this.name       = commandInfo.value();
        this.usage      = "/" + commandInfo.value() + " " + commandInfo.usage();
        this.permission = commandInfo.permission();
        this.aliases    = Arrays.asList(commandInfo.aliases());
    }

    public abstract void onCommand(Player player, String... args);

    public void sendUsage(Player player) {
        ChatHelper.sendMessage(player, "&8>> &cPoprawne uzycie komendy: &7" + this.usage + "&c.");
    }
}